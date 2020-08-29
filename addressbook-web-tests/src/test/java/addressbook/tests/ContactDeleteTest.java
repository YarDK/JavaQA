package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().contacts().size() == 0)
        {
            app.contact().create(new ContactData()
                    .withFirst_name("Delete_contact_first_name")
                    .withLast_name("Delete_contact_last_name")
                    .withMiddle_name("Delete_contact_middle_name")
                    .withNick_name("Delete_contact_nick_name")
                    .withTelephone_home("192873129783")
            );
        }
    }

    @Test
    public void testContactDeleteTest(){
        Contacts before = app.db().contacts();
        ContactData deleted_contact = before.iterator().next();
        app.contact().deleted(deleted_contact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deleted_contact)));
        verifyContactListInUI();
    }
}
