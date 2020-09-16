package bugify.appmanager;

import bugify.model.Issue;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;

import java.util.NoSuchElementException;
import java.util.Set;

public class RestHelper {


    public Issue getIssue(int issue_id) {
        try {
            return getIssues().stream().filter(i -> i.getId() == issue_id).findFirst().get();
        } catch (NoSuchElementException e){
            return new Issue().withStatus("closed");
        }
    }

    private Set<Issue> getIssues() {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

}
