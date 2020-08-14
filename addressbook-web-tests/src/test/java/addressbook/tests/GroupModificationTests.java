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
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("Modify_group"));
        }
    }


    @Test(enabled = false)
    public void testGroupModificationTests() {




        Groups before = app.group().all();
        GroupData modified_group = before.iterator().next();

        GroupData group = new GroupData()
                .withName("New_group_modify_name")
                .withFooter("Modify_footer")
                .withHeader("Modify_header")
                .withId(modified_group.getId());

        app.group().modify(group);

        Groups after = app.group().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withModify(modified_group, group)));
    }


}
