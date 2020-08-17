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
        type(By.name("mobile"), contactData.getTelephone_mobile());
        type(By.name("work"), contactData.getTelephone_work());

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
        waiter(500);
    }

    public void returnHome(){
        click(By.linkText("home"));
        waiter(500);
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
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", id)));

        // Из лекции:
        // By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))
        //
        // Поиск строки, в которой есть чекбокс с заданным идент-ром
        // By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))
    }

    public void submitContactUpdater(){
        click(By.name("update"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void create(ContactData contactData) {
        returnHome();
        initContactCreation();
        fillContactForm(contactData, true);
        submitContactCreation();
        contactCache = null;
        returnHomePage();
    }

    public void deleted(ContactData contact) {
        selectContactById(contact.getId());
        deleteContactButton();
        acceptAlertByDelete();
        contactCache = null;
        returnHome();
    }

    public void modify(ContactData contact) {
        clickEditIconById(contact.getId());
        fillContactForm(contact, false);
        submitContactUpdater();
        contactCache = null;
        returnHomePage();
    }

    public void refrashPage(){
        wd.navigate().refresh();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    private Contacts contactCache = null;

    public Contacts all() {

        if(contactCache != null){
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        if(isThereAContact()) {
            for (WebElement e : elements) {
                String last_name = e.findElements(By.tagName("td")).get(1).getText();
                String first_name = e.findElements(By.tagName("td")).get(2).getText();
                String address = e.findElements(By.tagName("td")).get(3).getText();
                String allEmails = e.findElements(By.tagName("td")).get(4).getText();
                String allPhones = e.findElements(By.tagName("td")).get(5).getText();
                int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
                ContactData contact = new ContactData()
                        .withFirst_name(first_name)
                        .withLast_name(last_name)
                        .withId(id)
                        .withAddress(address)
                        .withTelephone_all(allPhones)
                        .withEmail_all(allEmails);
                contactCache.add(contact);
            }
        }

        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        clickEditIconById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile= wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email_1 = wd.findElement(By.name("email")).getAttribute("value");
        String email_2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email_3 = wd.findElement(By.name("email3")).getAttribute("value");
        returnHome();

        return new ContactData()
                .withId(contact.getId())
                .withFirst_name(firstname)
                .withLast_name(lastname)
                .withAddress(address)
                .withTelephone_home(home)
                .withTelephone_mobile(mobile)
                .withTelephone_work(work)
                .withEmail_1(email_1)
                .withEmail_2(email_2)
                .withEmail_3(email_3);
    }
}
