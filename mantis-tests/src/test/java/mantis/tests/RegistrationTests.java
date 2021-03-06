package mantis.tests;

import mantis.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    //@BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    //@AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

    @Test
    public void testRegistrationTests() throws Exception {
        String email = String.format("user%s@localhost.localdomain", System.currentTimeMillis());
        String user_mail = "user" + System.currentTimeMillis();
        String password = "password";
        app.james().createUser(user_mail, password);
        app.registration().start(user_mail, email);
        //List<MailMessage> mailMessages = app.mail().waitFoMail(2, 10000);
        List<MailMessage> mailMessages = app.james().waitForMail(user_mail, password, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, "password");
        assertTrue(app.newSession().login(user_mail, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex()
                .find("http://")
                .nonSpace()
                .oneOrMore()
                .build();
        return regex.getText(mailMessage.text);
    }
}
