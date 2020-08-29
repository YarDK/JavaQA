package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if(app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("Modify_group"));
        }
    }


    @Test
    public void testGroupModificationTests() {
        Groups before = app.db().groups();
        GroupData modified_group = before.iterator().next();

        GroupData group = new GroupData()
                .withName("New_group_modify_name")
                .withFooter("Modify_footer")
                .withHeader("Modify_header")
                .withId(modified_group.getId());

        app.group().modify(group);
        assertEquals(app.group().count(), before.size());
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withModify(modified_group, group)));
    }


}
