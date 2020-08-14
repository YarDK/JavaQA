package addressbook.tests;

import addressbook.model.GroupData;

import addressbook.model.Groups;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        GroupData groupData = new GroupData().withName("New_group");


        app.goTo().groupPage();
        Groups before = app.group().all();

        app.group().create(groupData);
        Groups after = app.group().all();

        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(groupData.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    }

}
