package ru.stqa.training.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {

        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("FirstName1", "LastName1", "title", "Address", "5", "January", "2000", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
    }
}
