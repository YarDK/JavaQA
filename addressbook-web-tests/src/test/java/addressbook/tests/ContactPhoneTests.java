package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirst_name("New_contact_first_name")
                    .withLast_name("New_contact_last_name")
                    .withMiddle_name("New_contact_middle_name")
                    .withNick_name("New_contact_nick_name")
                    .withTelephone_home("192873129783")
                    .withTelephone_work("19287311121")
                    .withTelephone_mobile("1112231121")
                    .withGroup("[none]"));
        }
    }

    @Test
    public void testContactPhoneTests(){
        app.contact().returnHome();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getTelephone_all(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getTelephone_home(), contact.getTelephone_mobile(), contact.getTelephone_work())
                .filter(s -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }
}
