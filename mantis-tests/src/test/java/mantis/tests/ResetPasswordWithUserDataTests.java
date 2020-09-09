package mantis.tests;

import mantis.model.MailMessage;
import mantis.model.Users;
import mantis.model.UsersData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordWithUserDataTests extends TestBase {


    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetPasswordTests() throws Exception {
        String new_password = "newpassword";
        UsersData user = app.db().users().stream().filter(u -> u.getId() == 10).findAny().get();
        // Через UI авторизуемся под администратором
        app.newSessionUI().loginUI(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));

        // Через администратора сбрасываем пароль для ранее созданного пользователя
        app.resetPassword().reset(user.getUsername());

        // Идем на почту, вытягиваем ссылку на смену пароля
        List<MailMessage> new_mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLinkRefreshPassword = findConfirmationLinkOnMail(new_mailMessages.get(0));

        // Выходим из профиля администратора
        app.newSessionUI().logoutUI();

        // По полученной ссылке одновляем данные созданного пользователя
        app.resetPassword().refresh(user.getUsername(), confirmationLinkRefreshPassword, new_password);

        // На уровне HTTP проверяемя, что созданный пользователь можно авторизоваться с новым паролем
        assertTrue(app.newSession().login(user.getUsername(), new_password));

    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
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
