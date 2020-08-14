package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeleteTest extends TestBase {

    @Test
    public void testContactDeleteTest(){

        if(!app.getContactHelper().isThereAContact())
        {
            app.getContactHelper().createContact(new ContactData()
                    .withFirst_name("Delete_contact_first_name")
                    .withLast_name("Delete_contact_last_name")
                    .withMiddle_name("Delete_contact_middle_name")
                    .withNick_name("Delete_contact_nick_name")
                    .withTelephone_home("192873129783")
                    .withGroup("[none]"));
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData deleted_contact = before.iterator().next();
        app.getContactHelper().deletedContact(deleted_contact);
        waiter(1);
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() - 1);

    }


}
