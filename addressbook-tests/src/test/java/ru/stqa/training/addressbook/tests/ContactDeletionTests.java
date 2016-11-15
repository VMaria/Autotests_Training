package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {

        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("FirstName", "LastName", "title", "Address", "1", "March", "2000", "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().closeDialogWindow();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
