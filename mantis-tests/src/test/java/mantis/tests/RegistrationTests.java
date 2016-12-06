package mantis.tests;


import org.testng.annotations.Test;
import mantis.tests.TestBase;

public class RegistrationTests extends TestBase {

    @Test
    public void testRegistation() {
        TestBase.app.registration().start("user1", "test@test.com");

    }
}
