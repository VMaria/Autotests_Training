package ru.stqa.training.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {

        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("FirstName", "LastName", "title", "Address", "1", "March", "2000", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().closeDialogWindow();
    }
}
