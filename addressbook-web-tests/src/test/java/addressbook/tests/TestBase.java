package addressbook.tests;

import addressbook.appmanager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager("Firefox");

    @BeforeSuite
    public void setUp() {
        app.init();


    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }
}
