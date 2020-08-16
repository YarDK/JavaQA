package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirst_name("New_contact_first_name")
                    .withLast_name("New_contact_last_name")
                    .withMiddle_name("New_contact_middle_name")
                    .withNick_name("New_contact_nick_name")
                    .withEmail_1("email1@mail.ru")
                    .withEmail_2("email2@mail.ru")
                    .withEmail_3("email3@mail.ru")
                    .withGroup("[none]"));
        }
    }

    @Test
    public void testContactEmailTests(){
        app.contact().returnHome();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getEmail_all(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Stream.of(contact.getEmail_1(), contact.getEmail_2(), contact.getEmail_3())
                .filter(s -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
