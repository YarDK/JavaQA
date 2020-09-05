package mantis.tests;

import mantis.appmanager.HttpSession;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws Exception{
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}
