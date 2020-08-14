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

        if(!app.getContactHelper().isThereAContact())
        {
            app.getContactHelper().createContact(new ContactData()
                    .withFirst_name("New_contact_first_name")
                    .withLast_name("New_contact_last_name")
                    .withMiddle_name("New_contact_middle_name")
                    .withNick_name("New_contact_nick_name")
                    .withTelephone_home("192873129783")
                    .withGroup("[none]"));
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData modified_contact = before.iterator().next();

        ContactData contact = new ContactData()
                .withFirst_name("Modify_contact_first_name")
                .withLast_name("Modify_contact_last_name")
                .withId(modified_contact.getId());

        app.getContactHelper().modifyContact(contact);
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
        


    }

}
