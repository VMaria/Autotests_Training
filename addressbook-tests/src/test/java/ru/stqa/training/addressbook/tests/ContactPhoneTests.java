package ru.stqa.training.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFname("FirstName").withLname("LastName").withTitle("title").
                    withAddress("Tverskaya st. 8/1 app.11").withHomephone("111").withMobilephone("222").withWorkphone("333").withEmail("email").
                    withEmail2("email2").withEmail3("email3").withDay("5").withMonth("January").withYear("2000").withGroup("test1"));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        //assertThat(contact.getHomephone(), equalTo(clean(contactInfoFromEditForm.getHomephone())));
        //assertThat(contact.getMobilephone(), equalTo(clean(contactInfoFromEditForm.getMobilephone())));
        //assertThat(contact.getWorkphone(), equalTo(clean(contactInfoFromEditForm.getWorkphone())));
    }

    private String mergePhones(ContactData contact) {
       return Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone()).
                stream().filter((s) -> ! s.equals("")).map(ContactPhoneTests::clean).collect(Collectors.joining("\n"));
    }

    public static String clean(String phone) {

        return  phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
