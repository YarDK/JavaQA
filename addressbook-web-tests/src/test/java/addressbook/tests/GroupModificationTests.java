package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModificationTests(){
        app.getNavigationHelper().gotoGroupPage();
        if(!app.getGroupHelper().isThereAGroup()){

            app.getGroupHelper().createGroup(new GroupData(
                    "Delete_group", "Group_for_delete", "Group_footer"));

        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("New_group_name_2","2","3"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }
}
