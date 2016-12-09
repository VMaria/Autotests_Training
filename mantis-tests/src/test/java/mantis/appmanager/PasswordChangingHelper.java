package mantis.appmanager;


import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;

public class PasswordChangingHelper extends HelperBase {

    public PasswordChangingHelper(ApplicationManager app) {

        super(app);
    }
    public void change(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update_User']"));
    }
}
