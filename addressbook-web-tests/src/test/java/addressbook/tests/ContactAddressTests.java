package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirst_name("New_contact_first_name")
                    .withLast_name("New_contact_last_name")
                    .withMiddle_name("New_contact_middle_name")
                    .withNick_name("New_contact_nick_name")
                    .withAddress("ул. Пушкина\nдом.8\nкв.871")
                    .withGroup("[none]"));
        }
    }

    @Test
    public void testContactAddressTests(){
        app.contact().returnHome();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }
}
