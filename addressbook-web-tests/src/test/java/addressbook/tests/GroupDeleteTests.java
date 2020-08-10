package addressbook.tests;


import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class GroupDeleteTests extends TestBase {


    @Test
    public void testGroupDelete() {
        app.getNavigationHelper().gotoGroupPage();

        if (!app.getGroupHelper().isThereAGroup()) {

            app.getGroupHelper().createGroup(new GroupData(
                    "Delete_group", "Group_for_delete", "Group_footer"));

        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

}
