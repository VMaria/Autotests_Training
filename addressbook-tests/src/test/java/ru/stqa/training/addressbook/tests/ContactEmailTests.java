package ru.stqa.training.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{

        public Properties properties = new Properties();

        @BeforeMethod
        public void ensurePreconditions() throws IOException {
            String target = System.getProperty("target", "local");
            properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
            app.goTo().homePage();
            if (app.contact().list().size() == 0) {
                app.contact().create(new ContactData().withFname(properties.getProperty("contactfname")).withLname(properties.getProperty("contactlname")).
                        withTitle(properties.getProperty("contacttitle")).withAddress(properties.getProperty("contactaddress")).
                        withHomephone(properties.getProperty("contacthomephone")).withMobilephone(properties.getProperty("contactmobilephone")).
                        withWorkphone(properties.getProperty("contactworkphone")).withEmail(properties.getProperty("contactemail")).withEmail2(properties.getProperty("contactemail2")).
                        withEmail3(properties.getProperty("contactemail3")).withDay(properties.getProperty("contactday")).withMonth(properties.getProperty("contactmonth")).
                        withYear(properties.getProperty("contactyear")).withGroup(properties.getProperty("contactgroup")));
            }
        }

    @Test
    public void testContactEmails() throws IOException {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromViewForm = app.contact().infoFromViewForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromViewForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).
                stream().filter((s) -> ! s.equals("")).map(ContactEmailTests::clean).collect(Collectors.joining("\n"));
    }

    public static String clean(String email) {

        return  email.replaceAll("\\s", "");
    }
}
