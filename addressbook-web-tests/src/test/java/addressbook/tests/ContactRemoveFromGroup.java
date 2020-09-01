package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.security.acl.Group;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroup extends TestBase {

    private ContactData testing_contact;
    private GroupData testing_group;

    @BeforeMethod
    public void ensurePreconditions(){

        // Если нет групп, создаем новую
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("Test_group")
                    .withFooter("Group_footer")
                    .withHeader("Group_header"));
        }

        // Ести нет контактов, создаем контакт с группой
        Contacts contacts = app.db().contacts();
        if(contacts.size() == 0){
            app.contact().create(new ContactData()
                    .withFirst_name("Remove_group_contact")
                    .inGroup(app.db().groups().iterator().next()));
        }


        // Выбираем группу, в которой есть хотя бы один контакт
        for(GroupData g : app.db().groups()){
            if(!g.getContacts().isEmpty()){
                this.testing_group = g;
                break;
            }
        }

        // Считаем, сколько контактов без групп
        int countEmptyGroups = 0;
        for(ContactData c : contacts){
            if(c.getGroups().isEmpty()) countEmptyGroups ++;
        }

        // Если все контакты без групп, создаем контакт с группой
        if(countEmptyGroups == contacts.size()){
            this.testing_group = app.db().groups().iterator().next();
            String contact_name = "Remove_from_group_contact_198273641823764";
            app.contact().create(new ContactData()
                    .inGroup(this.testing_group)
                    .withFirst_name(contact_name));

            this.testing_contact = app.db().contacts().stream()
                    .filter(c -> c.getFirst_name().equals(contact_name))
                    .findAny()
                    .get();
        }

        // Выбираем любой контакт с заданной группой
        for(ContactData c : contacts){
            if(c.getGroups().contains(this.testing_group)){
                this.testing_contact = c;
                break;
            }
        }

    }

    @AfterMethod
    public void removeTestData(){
        Contacts before = app.db().contacts();
        app.contact().deleted(this.testing_contact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(this.testing_contact)));
    }


    @Test
    public void testContactRemoveFromGroup(){
        Groups before = this.testing_contact.getGroups();

        app.contact().returnHome();
        app.contact().showContactInGroup(this.testing_group.getName());
        app.contact().selectContactById(this.testing_contact.getId());
        app.contact().clickByRemoveFromGroup();

        Groups after = app.db().contacts().stream()
                .filter(c -> c.getId() == this.testing_contact.getId())
                .findAny()
                .get().getGroups();

        assertThat(after, equalTo(before.without(this.testing_group)));

    }
}
