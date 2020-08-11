package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

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

    public void returnHome(){
        click(By.linkText("home"));
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

    public void refrashPage(){
        wd.navigate().refresh();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        if(isThereAContact()) {
            for (WebElement e : elements) {
                String last_name = e.findElements(By.tagName("td")).get(1).getText();
                String first_name = e.findElements(By.tagName("td")).get(2).getText();
                int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
                ContactData contact = new ContactData(first_name, last_name, id);
                contacts.add(contact);
            }
        }

        return contacts;
    }
}
