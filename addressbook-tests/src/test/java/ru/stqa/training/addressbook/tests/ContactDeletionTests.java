package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;
import ru.stqa.training.addressbook.model.Contacts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

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
                    withYear(properties.getProperty("contactyear")).withGroup(properties.getProperty("contactgroup")));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDeletion() throws IOException {

        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        System.out.println(app.contact().count());
        System.out.println(before.size() - 1);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        before.remove(deletedContact);
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactListInUI();
    }
}
