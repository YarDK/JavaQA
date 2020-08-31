package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){

        // Проверяем, есть ли группы
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("Test_group")
                    .withFooter("Group_footer")
                    .withHeader("Group_header"));
        }

        // Проверяем, есть ли контакты
        Contacts contacts = app.db().contacts();
        if(contacts.size() == 0){
            app.contact().create(new ContactData()
            .withFirst_name("First_name")
            .inGroup(app.db().groups().iterator().next()));
        }

        //Проверяем, есть ли контакты с группой
        int notEmptyGroups = 0;
        for(ContactData c : contacts){
            if(!c.getGroups().isEmpty()) notEmptyGroups ++;
        }
        if(notEmptyGroups == 0){
            app.contact().create(new ContactData()
                    .withFirst_name("Remove_group_contact")
                    .inGroup(app.db().groups().iterator().next()));
        }

    }


    @Test
    public void testContactRemoveFromGroup(){
        Contacts before = app.db().contacts();
        ContactData contact = new ContactData();

        // Выбираем контакт, у которого есть хотябы одна
        for(ContactData c : before) {
            if(!c.getGroups().isEmpty()) {
                contact = c;
                break;
            }
        }

        GroupData group = contact.getGroups().iterator().next();
        app.contact().returnHome();
        app.contact().showContactInGroup(group.getName());
        app.contact().selectContactById(contact.getId());
        app.contact().clickByRemoveFromGroup();
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withModify(contact, contact.outGroup(group))));

    }
}
