package ru.stqa.training.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;

public class ContactPageTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFname("FirstName").withLname("LastName").
                    withAddress("Tverskaya st. 8/1 app.11").withHomephone("111").withMobilephone("222").withWorkphone("333").withEmail("email").
                    withEmail2("email2").withEmail3("email3"));
        }
    }

    @Test
    public void testContactPage() {

    }
}
