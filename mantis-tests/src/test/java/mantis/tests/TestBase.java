package mantis.tests;

import mantis.appmanager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;


public class TestBase {


    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", "Firefox"));

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
        app.ftp().upload(new File(
                "/Users/yaroslavkorotyshov/Desktop/JavaQA/mantis-tests/src/test/resources/config_inc.php"),
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

}
