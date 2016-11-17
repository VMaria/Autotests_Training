package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFname("FirstName").withLname("LastName").withTitle("title").withAddress("Address").
                    withDay("5").withMonth("January").withYear("2000").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification() {

        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFname("FirstName").withLname("LastName").withTitle("title").withAddress("Address").
                withDay("5").withMonth("January").withYear("2000").withGroup("test1");
        app.contact().modify(contact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        //Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        //before.sort(byId);
        //after.sort(byId);
        Assert.assertEquals(after, before);

    }
}
