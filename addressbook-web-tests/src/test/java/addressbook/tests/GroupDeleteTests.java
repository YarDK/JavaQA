package addressbook.tests;


import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class GroupDeleteTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("Modify_group"));
        }
    }


    @Test
    public void testGroupDelete() {
        List<GroupData> before = app.group().list();
        int last_index = before.size() - 1;
        app.group().delete(last_index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(last_index);
        Assert.assertEquals(before, after);
    }

}
