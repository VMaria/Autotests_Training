package mantis.tests;

import mantis.appmanager.HTTPSession;
import mantis.model.AccountData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertTrue;

public class PasswordChangingTests extends TestBase{



    @BeforeMethod
    public void startMailServer() {

        app.mail().start();
    }

    @Test
    public void testPasswordChanging() throws IOException {
        app.getDriver();
        app.session();
        app.goTo().manage();
        app.goTo().manageUsers();
        AccountData account = app.db().accounts().iterator().next();
        app.goTo().editUser(account.getId());
        app.goTo().reset();

        app.db().accounts();
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {

        app.mail().stop();
    }
}
