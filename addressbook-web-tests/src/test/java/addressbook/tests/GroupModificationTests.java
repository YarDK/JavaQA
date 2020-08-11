package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModificationTests() {
        app.getNavigationHelper().gotoGroupPage();

        if (!app.getGroupHelper().isThereAGroup()) {

            app.getGroupHelper().createGroup(new GroupData(
                    "Modify_group", "Modify_for_delete", "Modify_footer"));

        }



        List<GroupData> before = app.getGroupHelper().getGroupList();

        GroupData group = new GroupData(
                "New_group_name_2",
                "2",
                "3",
                (before.get(before.size()-1).getId())
        );

        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(group);

        Comparator<? super GroupData> byId_v1 = (g1,g2) -> Integer.compare(g1.getId(), g2.getId());
        Comparator<? super GroupData> byId_v2 = Comparator.comparingInt(GroupData::getId);
        before.sort(byId_v2);
        after.sort(byId_v1);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
