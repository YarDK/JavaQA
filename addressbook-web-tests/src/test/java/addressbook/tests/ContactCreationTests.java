package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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

        Contacts before = app.contact().all();
        app.contact().create(contact);
        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.
                withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));

    }
}
