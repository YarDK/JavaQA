package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation(){
        ContactData contact = new ContactData(
                "Contact_name_test_1",
                "Contact_last_name_1",
                "Contact_middle_name_1",
                "Contact_nick_name_1",
                "88002353535",
                "New_group_name_2"
        );

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnHomePage();
    }
}
