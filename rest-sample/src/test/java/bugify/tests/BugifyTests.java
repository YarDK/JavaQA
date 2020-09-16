package bugify.tests;

import bugify.model.Issue;
import org.testng.SkipException;
import org.testng.annotations.Test;


public class BugifyTests extends TestBase {

    @Test
    public void testCheckStatus(){
        // 270 271 272

    }

    @Test
    public void testCheckStatus_new(){
        Issue issue = new Issue().withId(270);
        try {
            skipIfNotFixed(issue.getId());
            System.out.println("Test 'testCheckStatus_new' completed!");
        } catch (SkipException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testCheckStatus_resolve(){
        Issue issue = new Issue().withId(272);
        try {
            skipIfNotFixed(issue.getId());
            System.out.println("Test 'testCheckStatus_resolve' completed!");
        } catch (SkipException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testCheckStatus_close(){
        Issue issue = new Issue().withId(271);
        try {
            skipIfNotFixed(issue.getId());
            System.out.println("Test 'testCheckStatus_close' completed!");
        } catch (SkipException e) {
            System.out.println(e);
        }
    }
}
