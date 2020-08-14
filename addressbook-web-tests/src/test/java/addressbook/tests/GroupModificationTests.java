package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("Modify_group"));
        }
    }


    @Test
    public void testGroupModificationTests() {




        Set<GroupData> before = app.group().all();
        GroupData modified_group = before.iterator().next();

        GroupData group = new GroupData()
                .withName("New_group_modify_name")
                .withFooter("Modify_footer")
                .withHeader("Modify_header")
                .withId(modified_group.getId());

        app.group().modify(group);

        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modified_group);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }


}
