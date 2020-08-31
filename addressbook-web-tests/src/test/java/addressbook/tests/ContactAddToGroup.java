package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("Test_group")
                    .withFooter("Group_footer")
                    .withHeader("Group_header"));
        }
    }


    @Test
    public void testContactAddToGroup(){
        Contacts before = app.db().contacts();
        ContactData contact = app.db().contacts().iterator().next();
        GroupData group = app.db().groups().iterator().next();
        app.contact().addGroup(contact, group);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withModify(contact, contact.inGroup(group))));

    }

}
