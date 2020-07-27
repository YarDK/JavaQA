package addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTest extends TestBase {

    @Test
    public void testContactDeleteTest(){
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContactButton();
        app.getContactHelper().acceptAlertByDelete();
    }
}
