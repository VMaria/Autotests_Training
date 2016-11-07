package ru.stqa.training.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        app.getContactHelper().createContact(new ContactData("FirstName", "LastName", "title", "Address", "1", "March", "2000", "test1"));
        app.getNavigationHelper().gotoHomePage();
    }
}

