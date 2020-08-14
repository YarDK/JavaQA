package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("Modify_group"));
        }
    }


    @Test
    public void testGroupModificationTests() {




        List<GroupData> before = app.group().list();
        int last_index = before.size() - 1;

        GroupData group = new GroupData()
                .withName("New_group_modify_name")
                .withFooter("Modify_footer")
                .withHeader("Modify_header")
                .withId(before.get(before.size()-1).getId());

        app.group().modify(last_index, group);

        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(last_index);
        before.add(group);

        Comparator<? super GroupData> byId_v1 = (g1,g2) -> Integer.compare(g1.getId(), g2.getId());
        Comparator<? super GroupData> byId_v2 = Comparator.comparingInt(GroupData::getId);
        before.sort(byId_v2);
        after.sort(byId_v1);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }


}
