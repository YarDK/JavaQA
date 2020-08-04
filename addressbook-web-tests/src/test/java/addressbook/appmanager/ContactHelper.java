package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ContactHelper extends HelperBase {

    protected ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation(){
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean creation){
        type(By.name("firstname"), contactData.getFirst_name());
        type(By.name("middlename"), contactData.getMiddle_name());
        type(By.name("lastname"), contactData.getLast_name());
        type(By.name("nickname"), contactData.getNick_name());
        type(By.name("home"), contactData.getTelephone_home());

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void returnHomePage(){
        click(By.linkText("home page"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void acceptAlertByDelete(){
        acceptAlert();
    }

    public void deleteContactButton(){
        click(By.xpath("//input[@value='Delete']"));
    }

    public void clickEditIcon(){
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactUpdata(){
        click(By.name("update"));
    }

    public void createContact(ContactData contactData) {
        initContactCreation();
        fillContactForm(contactData, true);
        submitContactCreation();
        returnHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
