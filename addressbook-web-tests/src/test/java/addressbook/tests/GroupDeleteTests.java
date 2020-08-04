package addressbook.tests;


import addressbook.model.GroupData;
import org.testng.annotations.Test;


public class GroupDeleteTests  extends TestBase{



  @Test
  public void testGroupDelete() {
    app.getNavigationHelper().gotoGroupPage();
    if(!app.getGroupHelper().isThereAGroup()){

        app.getGroupHelper().createGroup(new GroupData(
                "Delete_group", "Group_for_delete", "Group_footer"));

    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }

}
