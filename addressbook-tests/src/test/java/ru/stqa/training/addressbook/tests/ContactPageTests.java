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

public class ContactPageTests extends TestBase{

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
    public void testContactPages() throws IOException {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        app.goTo().homePage();
        ContactData contactInfoFromViewForm = app.contact().infoFromViewForm(contact);
        System.out.println("info from edit= " + contactInfoFromEditForm);
        System.out.println("info from view= " + contactInfoFromViewForm);
        assertThat((contactInfoFromEditForm), equalTo(contactInfoFromViewForm));
    }

    private String mergeAll(ContactData contact) {
        return Arrays.asList(contact.getFname(),contact.getLname(),contact.getAddress(),
               contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone(),
                contact.getEmail(),contact.getEmail2(),contact.getEmail3()).
                stream().filter((s) -> ! s.equals("")).map(ContactPageTests::clean).collect(Collectors.joining("\n"));
    }

    public static String clean(String contact) {

        return  contact.replaceAll("\\s", "").replaceAll("[-()]", "");
    }


}
