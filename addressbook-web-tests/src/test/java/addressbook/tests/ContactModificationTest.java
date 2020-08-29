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
                .withId(modified_contact.getId())
                .withFirst_name("Modify_first_name")
                .withLast_name("Modify_Last_name" )
                .withMiddle_name(modified_contact.getMiddle_name())
                .withNick_name(modified_contact.getNick_name())
                .withAddress(modified_contact.getAddress())
                .withTelephone_home(modified_contact.getTelephone_home())
                .withTelephone_mobile(modified_contact.getTelephone_mobile())
                .withTelephone_work(modified_contact.getTelephone_work())
                .withEmail_1(modified_contact.getEmail_1())
                .withEmail_2(modified_contact.getEmail_2())
                .withEmail_3(modified_contact.getEmail_3());

        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withModify(modified_contact, contact)));
        verifyContactListInUI();
    }
}
