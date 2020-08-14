package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        GroupData groupData = new GroupData().withName("New_group");


        app.goTo().groupPage();
        List<GroupData>  before = app.group().list();

        app.group().create(groupData);
        List<GroupData>  after = app.group().list();

        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(groupData);
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
