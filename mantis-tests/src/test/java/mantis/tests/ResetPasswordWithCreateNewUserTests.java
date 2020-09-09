package mantis.tests;

import mantis.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordWithCreateNewUserTests extends TestBase {

    private final long now = System.currentTimeMillis();
    private final String email = String.format("user%s@localhost.localdomain", now);
    private final String user = "user" + now;
    private final String password = "password";
    private final String new_password = "newpassword";


    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
        // Регистрируем нового пользователя
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLinkRegistration = findConfirmationLinkOnMail(mailMessages.get(0));
        app.registration().finish(confirmationLinkRegistration, password);

        // Выходим из аккаунта нового пользователя
        app.newSessionUI().logoutUI();
    }

    @Test
    public void testResetPasswordTests() throws Exception {
         // Через UI авторизуемся под администратором
        app.newSessionUI().loginUI(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));

        // Через администратора сбрасываем пароль для ранее созданного пользователя
        app.resetPassword().reset(user);

        // Идем на почту, вытягиваем ссылку на смену пароля
        List<MailMessage> new_mailMessages = app.mail().waitForMail(3, 10000);
        String confirmationLinkRefreshPassword = findConfirmationLinkOnMail(new_mailMessages.get(2));

        // Выходим из профиля администратора
        app.newSessionUI().logoutUI();

        // По полученной ссылке одновляем данные созданного пользователя
        app.resetPassword().refresh(user, confirmationLinkRefreshPassword, new_password);

        // На уровне HTTP проверяемя, что созданный пользователь можно авторизоваться с новым паролем
        assertTrue(app.newSession().login(user, new_password));

    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

    private String findConfirmationLinkOnMail(MailMessage mailMessage) {
        VerbalExpression regex = VerbalExpression.regex()
                .find("http://")
                .nonSpace()
                .oneOrMore()
                .build();
        return regex.getText(mailMessage.text);
    }
}
