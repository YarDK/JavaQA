package bugify.tests;

import bugify.appmanager.ApplicationManager;
import bugify.model.Issue;
import org.testng.SkipException;

public class TestBase {
    protected static final ApplicationManager app = new ApplicationManager();


    public boolean isIssueOpen(int issueId)  {
        Issue issue =  app.restHelper().getIssueById(issueId);
        System.out.println(issue.getState_name());
        return issue.getState_name().equals("Resolved") || issue.getState_name().equals("Closed");
    }

    public void skipIfNotFixed(int issueId) {
        if (!isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId + " not closed");
        }
    }

}
