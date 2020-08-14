package addressbook.tests;


import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;


public class GroupDeleteTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("Modify_group"));
        }
    }


    @Test
    public void testGroupDelete() {
        Set<GroupData> before = app.group().all();
        GroupData deleted_group = before.iterator().next();
        app.group().delete(deleted_group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deleted_group);
        Assert.assertEquals(before, after);
    }

}
