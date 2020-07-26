package addressbook;


import org.testng.annotations.Test;


public class GroupDeleteTests  extends TestBase{



  @Test
  public void testGroupDelete() {
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }

}
