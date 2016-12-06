package mantis.tests;

import mantis.appmanager.HTTPSession;
import org.testng.annotations.Test;
import java.io.IOException;

import static org.testng.AssertJUnit.assertTrue;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HTTPSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}
