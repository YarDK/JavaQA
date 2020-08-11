package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModificationTest(){
        ContactData contact = new ContactData(
                "Contact_name_test_update_2",
                "Contact_last_name_update_2",
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

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().clickEditIcon();
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactUpdata();
        app.getContactHelper().returnHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        // Редактирование происходит для рандомного контакта, т.к. условиями не заданно, какой именно контакт долженё
        // изменяться

        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

        //System.out.println(before.toString());
        //System.out.println(after.toString());


    }
}
