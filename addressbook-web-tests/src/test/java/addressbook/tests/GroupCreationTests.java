package addressbook.tests;

import addressbook.model.GroupData;

import addressbook.model.Groups;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test(enabled = false)
    public void testGroupCreation() {

        GroupData groupData = new GroupData().withName("New_group");
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(groupData);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(groupData.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() {

        GroupData groupData = new GroupData().withName("New_group'"); // С апострофом группа не может быть создана
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(groupData);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }

}
