package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.*;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {

    GroupData groupData = new GroupData(
            "New_group_name_2",
            "Group_header_logo",
            "Group_footer_comment"
    );

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(groupData);
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
  }

}
