package addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {

    GroupData groupData = new GroupData(
            "New_group_name_2",
            "Group_header_logo",
            "Group_footer_comment"
    );

    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(groupData);
    submitGroupCreation();
    returnToGroupPage();
  }

}
