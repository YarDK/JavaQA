package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        GroupData groupData = new GroupData(
                "New_group_name_4",
                null,
                null
                );


        app.getNavigationHelper().gotoGroupPage();
        List<GroupData>  before = app.getGroupHelper().getGroupList();

        app.getGroupHelper().createGroup(groupData);
        List<GroupData>  after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size() + 1);

        /*
        int max_old = 0;
        for(GroupData g : after){
            if(g.getId() > max_old) {
                max_old = g.getId();
            }
        }

        //Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(),o2.getId());

        int max = after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId();
        groupData.setId(max);
        */


        before.add(groupData);
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
