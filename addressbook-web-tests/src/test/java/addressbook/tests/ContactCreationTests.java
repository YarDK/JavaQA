package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if(app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("New_group_creation_test"));
        }
    }

    @DataProvider
    public Iterator<Object[]> validContact_json() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("src/test/resources/contacts.json")))
        {
            String json = "";
            String line = br.readLine();
            while (line != null) {
                json += line;
                line = br.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType()); // List<ContactData>.class
            return contacts.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContact_json")
    public void testContactCreation(ContactData contact){
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        app.contact().create(contact.inGroup(groups.iterator().next()));
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
        verifyContactListInUI();
    }
}
