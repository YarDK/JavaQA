package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

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
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContactButton();
        app.getContactHelper().acceptAlertByDelete();
    }
}
