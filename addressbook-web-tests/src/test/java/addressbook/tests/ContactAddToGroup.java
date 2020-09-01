package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase {

    private ContactData testing_contact;
    private GroupData testing_group;

    @BeforeMethod
    public void ensurePreconditions(){

        // Если нет групп, создаем группу
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("Test_group")
                    .withFooter("Group_footer")
                    .withHeader("Group_header"));
        }

        // Если нет контактов, создаем контакт
        if(app.db().contacts().size() == 0){
            app.contact().create(new ContactData()
                    .withFirst_name("Contact_for_group_add")
                    .withLast_name("last_name")
                    .withMiddle_name("middle_name")
                    .withTelephone_home("192873129783"));

        }

        // Определяем, сколько контактов имеют все группы
        int count_full_groups = 0;
        for(ContactData c : app.db().contacts()){
            if(c.getGroups().equals(app.db().groups())){
                count_full_groups++;
            } else {
                this.testing_contact = c;
            }
        }

        // Если все контакты имеют все группы, то создаем новую группу
        // и берем на тест любой контакт
        if(count_full_groups == app.db().contacts().size()){
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("Test_group_for_add_contact")
                    .withFooter("Group_footer")
                    .withHeader("Group_header"));
            this.testing_contact = app.db().contacts().iterator().next();
        }

        // Для выбранного контакта выбираем группу, которой нет в списке групп контакта
        for(GroupData g : app.db().groups()){
            if(!this.testing_contact.getGroups().contains(g)){
                this.testing_group = g;
            }
        }
    }


    @Test
    public void testContactAddToGroup(){
        Groups before = this.testing_contact.getGroups();
        app.contact().addGroup(this.testing_contact, this.testing_group);
        Groups after = app.db().contacts().stream()
                .filter(c -> c.getId() == this.testing_contact.getId())
                .findAny()
                .get().getGroups();

        assertThat(after, equalTo(before.withAdded(this.testing_group)));
    }
}
