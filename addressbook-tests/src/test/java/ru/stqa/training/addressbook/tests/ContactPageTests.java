package ru.stqa.training.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPageTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFname("FirstName").withLname("LastName").
                    withAddress("Tverskaya st. 8/1 app.11").
                    withHomephone("111").withMobilephone("222").withWorkphone("333").withEmail("email").
                    withEmail2("email2").withEmail3("email3"));
        }
    }

    @Test
    public void testContactPages() {
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
