package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Set;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        GroupData groupData = new GroupData().withName("New_group");


        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();

        app.group().create(groupData);
        Set<GroupData>  after = app.group().all();

        Assert.assertEquals(after.size(), before.size() + 1);

        groupData.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt());
        before.add(groupData);
        Assert.assertEquals(before,after);
    }

}
