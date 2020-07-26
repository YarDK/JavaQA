package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    protected NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
     click(By.linkText("groups"));
    }
}
