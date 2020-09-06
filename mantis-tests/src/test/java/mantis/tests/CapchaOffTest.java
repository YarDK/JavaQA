package mantis.tests;

import org.testng.annotations.Test;

public class CapchaOffTest extends TestBase{

    @Test
    public void testCapchaOffTest(){
        app.registration().start("user1", "mail@mail.ru");
    }
}
