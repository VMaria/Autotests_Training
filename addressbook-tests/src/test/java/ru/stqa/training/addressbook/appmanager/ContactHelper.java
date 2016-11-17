package ru.stqa.training.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.addressbook.model.ContactData;
import ru.stqa.training.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void submitContactCreation() {

        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {

        type(By.name("firstname"), contactData.getFname());
        type(By.name("lastname"), contactData.getLname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("address"), contactData.getAddress());
        select(By.name("bday"), contactData.getDay());
        select(By.name("bmonth"), contactData.getMonth());
        type(By.name("byear"), contactData.getYear());
        if (creation) {

            select(By.name("new_group"), contactData.getGroup());
        } else {

            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void initContactCreation() {

        click(By.linkText("add new"));
    }

    public void initContactModification(int id) {

        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification() {

        click(By.name("update"));
    }

    public void selectContact(int index) {

        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {

        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {

        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void closeDialogWindow() {

        wd.switchTo().alert().accept();
    }

    public boolean isThereAContact() {

        return isElementPresent(By.xpath("//tr[@name='entry']"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
    }


    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        closeDialogWindow();
    }


    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
                String fname = element.findElement(By.xpath(".//td[3]")).getText();
                String lname = element.findElement(By.xpath(".//td[2]")).getText();
                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                ContactData contact = new ContactData().withId(id).withFname(fname).withLname(lname).withTitle("title").withAddress("Address").
                        withDay("5").withMonth("January").withYear("2000").withGroup("test1");
                contacts.add(contact);
            }
        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String fname = element.findElement(By.xpath(".//td[3]")).getText();
            String lname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFname(fname).withLname(lname).withTitle("title").withAddress("Address").
                    withDay("5").withMonth("January").withYear("2000").withGroup("test1"));
        }
        return contacts;
    }
}
