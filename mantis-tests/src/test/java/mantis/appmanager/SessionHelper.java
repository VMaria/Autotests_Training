package mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager app) {

        super(app);
    }

    public void login(String username, String password) {

        type (By.name("username"), username);
        type (By.name("password"), password);
        click (By.cssSelector("input[value='Войти']"));
    }
}
