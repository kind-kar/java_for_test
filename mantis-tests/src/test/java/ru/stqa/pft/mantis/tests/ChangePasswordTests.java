package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mailHelper().start();
    }

    @Test
    public void testChangePassword() throws IOException, InterruptedException {
        String email = "user1@localhost.dete";
        String user = "user13";
        String password = "password";
        String newPassword = "newPassword";
        String admin = "administrator";
        Integer userId = app.db().userId(user);
        app.change().init(admin, password);
        app.newSession().login(password, admin);
        app.change().start(userId);
        List<MailMessage> mailMessages = app.mailHelper().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.change().finish(confirmationLink, newPassword);
        assertTrue(app.newSession().login(newPassword, user));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mailHelper().stop();
    }
}
