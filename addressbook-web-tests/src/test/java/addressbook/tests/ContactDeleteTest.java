package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeleteTest extends TestBase {

    @Test
    public void testContactDeleteTest(){
        if(!app.getContactHelper().isThereAContact()){

            app.getContactHelper().createContact(new ContactData(
                    "Delete_contact_first_name",
                    "Delete_contact_last_name",
                    "Delete_contact_middle_name",
                    "Delete_contact_nick_name",
                    "192873129783",
                    "[none]"));

        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContactButton();
        app.getContactHelper().acceptAlertByDelete();
        app.getContactHelper().returnHome();
        waiter(1);
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() - 1);

    }
}
