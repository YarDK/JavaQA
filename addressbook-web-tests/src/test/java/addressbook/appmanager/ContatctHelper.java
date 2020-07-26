package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContatctHelper extends HelperBase {
    private WebDriver wd;

    protected ContatctHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation(){
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData){
        type(By.name("firstname"), contactData.getFirst_name());
        type(By.name("middlename"), contactData.getMiddle_name());
        type(By.name("lastname"), contactData.getLast_name());
        type(By.name("nickname"), contactData.getNick_name());
        type(By.name("home"), contactData.getTelephone_home());

    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void returnHomePage(){
        click(By.linkText("home page"));
    }

}
