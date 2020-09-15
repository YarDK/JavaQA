package mantis.tests;

import mantis.appmanager.ApplicationManager;
import mantis.model.Issue;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class TestBase {


    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", "Firefox"));

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
        app.ftp().upload(new File(
                "src/test/resources/config_inc.php"),
                "config_inc.php",
                "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore(
                "config_inc.php",
                "config_inc.php.bak");
        app.stop();
    }


    // new resolved closed
    public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        Issue issue = app.soap().getIssue(issueId);
        return issue.getStatus().equals("resolved") || issue.getStatus().equals("closed");
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (!isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
