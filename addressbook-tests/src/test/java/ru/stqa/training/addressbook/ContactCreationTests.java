package ru.stqa.training.addressbook;

import org.testng.annotations.Test;


public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {

        initContactCreation();
        fillContactForm(new ContactData("FirstName", "LastName", "title", "Address", "//div[@id='content']/form/select[1]//option[3]", "//div[@id='content']/form/select[2]//option[2]", "2000"));
        submitContactCreation();
        gotoHomePage();
    }

}

