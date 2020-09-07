package mantis.appmanager;

import org.openqa.selenium.By;

public class UISession extends HelperBase {

    public UISession(ApplicationManager app) {
        super(app);
    }

    public void loginUI(String user, String password){
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.id("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.id("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public void logoutUI(){
        wd.get(app.getProperty("web.baseUrl") + "/logout_page.php");
    }

}
