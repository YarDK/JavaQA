package addressbook.tests;


import addressbook.model.GroupData;
import addressbook.model.Groups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;


public class GroupDeleteTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if(app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("Modify_group"));
        }
    }


    @Test
    public void testGroupDelete() {
        Groups before = app.db().groups();
        GroupData deleted_group = before.iterator().next();
        app.group().delete(deleted_group);
        assertEquals(app.group().count(), before.size() - 1);
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(deleted_group)));
    }

}
