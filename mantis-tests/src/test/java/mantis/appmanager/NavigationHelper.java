package mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {

        super(app);
    }


    public void manage() {

        //click(By.cssSelector("a[href='/mantisbt-1.3.4/manage_overview_page.php']"));
        click(By.xpath("//ul[@id='menu-items']//li[.='Manage']"));
    }

    public void manageUsers() {

        click(By.cssSelector("a[href='/mantisbt-1.3.4/manage_user_page.php']"));
    }

    public void editUser(int id) {
        click (By.cssSelector("a[manage_user_edit_page.php?user_id="+ id +"]"));
    }

    public void reset () {
        click(By.cssSelector("input[value='Reset Password']"));
    }
}
