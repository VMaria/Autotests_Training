package mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {

        super(app);
    }


    public void manage() {

        click(By.cssSelector("a[href='/mantisbt-1.3.4/manage_overview_page.php']"));
    }

    public void manageUsers() {
        click(By.cssSelector("a[href='/mantisbt-1.3.4/manage_user_page.php']"));
    }
}
