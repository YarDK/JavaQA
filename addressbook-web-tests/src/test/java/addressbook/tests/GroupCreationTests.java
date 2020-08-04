package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.*;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {

    GroupData groupData = new GroupData(
            "New_group_name_2",
            null,
            null
    );

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(groupData);
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
  }

}
