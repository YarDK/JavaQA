package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.List;


public class ContactHelper extends HelperBase {

    protected ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation(){
        By link = By.linkText("add new");
        waitForElementPresent(link, "Href 'add new' not present or found", 5);
        click(link);
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


    public void selectContactById(int id) {
        click(By.cssSelector("input[id='" + id + "']"));
    }

    public void acceptAlertByDelete(){
        acceptAlert();
    }

    public void deleteContactButton(){
        click(By.xpath("//input[@value='Delete']"));
    }

    public void clickEditIconById(int id){
        click(By.cssSelector("a[href='edit.php?id=" + id + "']"));
    }

    public void submitContactUpdata(){
        click(By.name("update"));
    }

    public void create(ContactData contactData) {
        returnHome();
        initContactCreation();
        fillContactForm(contactData, true);
        submitContactCreation();
        returnHomePage();
    }

    public void deleted(ContactData contact) {
        selectContactById(contact.getId());
        deleteContactButton();
        acceptAlertByDelete();
        returnHome();
    }

    public void modify(ContactData contact) {
        clickEditIconById(contact.getId());
        fillContactForm(contact, false);
        submitContactUpdata();
        returnHomePage();
    }

    public void refrashPage(){
        wd.navigate().refresh();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        if(isThereAContact()) {
            for (WebElement e : elements) {
                String last_name = e.findElements(By.tagName("td")).get(1).getText();
                String first_name = e.findElements(By.tagName("td")).get(2).getText();
                int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
                ContactData contact = new ContactData().withFirst_name(first_name).withLast_name(last_name).withId(id);
                contacts.add(contact);
            }
        }
        return contacts;
    }
}
