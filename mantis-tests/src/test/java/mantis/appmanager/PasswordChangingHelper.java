package mantis.appmanager;


import mantis.model.AccountData;
import mantis.model.Accounts;
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

    public AccountData selectAccount() {
        AccountData account = app.db().accounts().iterator().next();
    Accounts accounts = app.db().accounts();
        for (AccountData account1: accounts) {
        if (account1.getName() != "administrator")
            account = account1;
            return account;
        }
        return account;
    }
}
