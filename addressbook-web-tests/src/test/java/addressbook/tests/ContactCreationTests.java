package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation(){

        ContactData contact = new ContactData()
                .withFirst_name("Contact_name_test_3")
                .withLast_name("Contact_last_name_3")
                .withMiddle_name("Contact_middle_name_1")
                .withNick_name("Contact_nick_name_1")
                .withTelephone_home("88002353535")
                .withGroup("New_group_name_2");

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);




    }


}
