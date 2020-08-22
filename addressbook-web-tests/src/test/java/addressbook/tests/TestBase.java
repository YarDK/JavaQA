package addressbook.tests;

import addressbook.appmanager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;


public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", "Firefox"));

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }
}
