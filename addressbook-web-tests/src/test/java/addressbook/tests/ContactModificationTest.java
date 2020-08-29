package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirst_name("New_contact_first_name")
                    .withLast_name("New_contact_last_name")
                    .withMiddle_name("New_contact_middle_name")
                    .withNick_name("New_contact_nick_name")
                    .withTelephone_home("192873129783")
            );
        }
    }


    @Test
    public void testContactModificationTest(){
        Contacts before = app.db().contacts();
        ContactData modified_contact = before.iterator().next();

        ContactData contact = new ContactData()
                .withFirst_name("Modify_contact_first_name")
                .withLast_name("Modify_contact_last_name")
                .withId(modified_contact.getId());

        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withModify(modified_contact, contact)));

    }
}
