package rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

public class RestAssueredTests {

    @BeforeClass
    public void init(){
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    @Test
    public void testCreatedIssue() {
        Set<IssueRest> oldIssueRests = getIssues();
        IssueRest newIssueRest = new IssueRest().withSubject("Test issue yardk Resolved").withDescription("hgfghjfgh");
        int issueId = createIssue(newIssueRest);
        Set<IssueRest> newIssueRests = getIssues();
        oldIssueRests.add(newIssueRest.withId(issueId));
        Assert.assertEquals(newIssueRests, oldIssueRests);

    }

    private int createIssue(IssueRest newIssueRest) {
        String json = RestAssured.given()
                .parameter("subject", newIssueRest.getSubject())
                .parameter("description", newIssueRest.getDescription())
                .post("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    private Set<IssueRest> getIssues() {
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<IssueRest>>(){}.getType());
    }

}
