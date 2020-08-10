package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        GroupData groupData = new GroupData(
                "New_group_name_3",
                null,
                null
        );


        app.getNavigationHelper().gotoGroupPage();
        List<GroupData>  before = app.getGroupHelper().getGroupList();

        app.getGroupHelper().createGroup(groupData);
        List<GroupData>  after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size() + 1);
    }

}
