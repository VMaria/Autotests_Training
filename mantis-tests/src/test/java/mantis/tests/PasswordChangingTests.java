package mantis.tests;

import mantis.appmanager.HTTPSession;
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
        HTTPSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        app.goTo().manage();
        app.goTo().manageUsers();

    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {

        app.mail().stop();
    }
}
