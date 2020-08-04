package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModificationTest(){
        ContactData contact = new ContactData(
                "Contact_name_test_update_1",
                "Contact_last_name_update_1",
                "Contact_middle_name_update_1",
                "Contact_nick_name_update_1",
                "84950001122",
                null
        );

        if(!app.getContactHelper().isThereAContact()){

            app.getContactHelper().createContact(new ContactData(
                    "Delete_contact_first_name",
                    "Delete_contact_last_name",
                    "Delete_contact_middle_name",
                    "Delete_contact_nick_name",
                    "192873129783",
                    "[none]"));

        }

        app.getContactHelper().clickEditIcon();
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactUpdata();
        app.getContactHelper().returnHomePage();

        // Редактирование происходит для рандомного контакта, т.к. условиями не заданно, какой именно контакт долженё
        // изменяться

    }
}
