package mantis.appmanager;

import org.openqa.selenium.By;

public class ResetPasswordHelper extends HelperBase {

    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void reset(String user) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        click(By.linkText(user));
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    public void refresh(String user, String url, String new_password) {
        wd.get(url);
        type(By.id("realname"), user);
        type(By.id("password"), new_password);
        type(By.id("password-confirm"), new_password);
        click(By.cssSelector("button[type='submit']"));
    }

}
