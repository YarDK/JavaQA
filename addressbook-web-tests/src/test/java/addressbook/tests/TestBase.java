package addressbook.tests;

import addressbook.appmanager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        app.init("Firefox");


    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    public void waiter(int second){
        try{
            Thread.sleep(second * 1000);
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
