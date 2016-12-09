package mantis.tests;

import mantis.model.AccountData;
import mantis.model.Accounts;
import mantis.model.MailMessage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;


public class PasswordChangingTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {

        app.mail().start();
    }

    @Test
    public void testPasswordChanging() throws IOException {
        String password = "password";
        app.getDriver();
        app.session();
        app.goTo().manage();
        app.goTo().manageUsers();
        AccountData account = app.password().selectAccount();
        app.goTo().editUser(account.getId());
        app.goTo().reset();
        List<MailMessage> mailMessages = app.mail().waitForMail(10, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, account.getEmail());
        System.out.println("confirmation link    "+confirmationLink);
        app.password().change(confirmationLink, password);
        Assert.assertTrue(app.newSession().login(account.getName(), password));
        app.db().accounts();
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {

        app.mail().stop();
    }
}
