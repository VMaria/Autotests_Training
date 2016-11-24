package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;
import ru.stqa.training.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

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
    public void testContactDeletion() {

        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        System.out.println(app.contact().count());
        System.out.println(before.size() - 1);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.contact().all();
        before.remove(deletedContact);
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
