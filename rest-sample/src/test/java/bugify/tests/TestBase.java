package bugify.tests;

import bugify.appmanager.ApplicationManager;
import bugify.model.Issue;
import org.testng.SkipException;

public class TestBase {
    protected static final ApplicationManager app = new ApplicationManager();


    public boolean isIssueOpen(int issueId)  {
        Issue issue =  app.restHelper().getIssue(issueId);
        return issue.getState_name().equals("resolved") || issue.getState_name().equals("closed");
    }

    public void skipIfNotFixed(int issueId) {
        if (!isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId + " not closed");
        }
    }

}
