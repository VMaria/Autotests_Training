package ru.stqa.training.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;
import ru.stqa.training.addressbook.model.Contacts;
import ru.stqa.training.addressbook.model.Groups;
import ru.stqa.training.addressbook.model.GroupData;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddingContactToGroupTests extends TestBase{

    public Properties properties = new Properties();

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFname(properties.getProperty("contactfname")).withLname(properties.getProperty("contactlname")).
                    withTitle(properties.getProperty("contacttitle")).withAddress(properties.getProperty("contactaddress")).
                    withHomephone(properties.getProperty("contacthomephone")).withMobilephone(properties.getProperty("contactmobilephone")).
                    withWorkphone(properties.getProperty("contactworkphone")).withEmail(properties.getProperty("contactemail")).withEmail2(properties.getProperty("contactemail2")).
                    withEmail3(properties.getProperty("contactemail3")).withDay(properties.getProperty("contactday")).withMonth(properties.getProperty("contactmonth")).
                    withYear(properties.getProperty("contactyear")));
            app.goTo().homePage();
        }

        System.out.println("group size    "  + app.db().groups().size());

        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName(properties.getProperty("groupname")));
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddingContactToGroup() throws IOException {

        app.goTo().homePage();
        ContactData addedToGroupContact = app.db().contacts().iterator().next();
        GroupData group = app.db().groups().iterator().next();
        app.contact().addToGroup(addedToGroupContact, group);
        app.goTo().homePage();
        verifyContactListInUI();
    }
}
