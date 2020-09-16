package rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class RestTests {

    @Test
    public void testCreatedIssue() throws IOException {
        Set<IssueRest> oldIssueRests = getIssues();
        IssueRest newIssueRest = new IssueRest().withSubject("Test issue yardk").withDescription("hgfghjfgh");
        int issueId = createIssue(newIssueRest);
        Set<IssueRest> newIssueRests = getIssues();
        oldIssueRests.add(newIssueRest.withId(issueId));
        Assert.assertEquals(newIssueRests, oldIssueRests);

    }

    private int createIssue(IssueRest newIssueRest) throws IOException {
        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssueRest.getSubject()),
                          new BasicNameValuePair("description", newIssueRest.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    private Set<IssueRest> getIssues() throws IOException{
        String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<IssueRest>>(){}.getType());
    }

    private Executor getExecutor() {
        String api_key = "288f44776e7bec4bf44fdfeb1e646490";
        //System.out.println(Base64.getEncoder().encodeToString(api_key.getBytes()));
        return Executor.newInstance().auth(api_key, "");
    }
}
