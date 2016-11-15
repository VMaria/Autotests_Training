package ru.stqa.training.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

    public void initContactModification(int index) {

        wd.findElements(By.cssSelector("img[src='icons/pencil.png']")).get(index).click();
    }

    public void submitContactModification() {

        click(By.name("update"));
    }

    public void selectContact(int index) {

        wd.findElements(By.name("selected[]")).get(index).click();
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

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
                String fname = element.findElement(By.xpath(".//td[3]")).getText();
                String lname = element.findElement(By.xpath(".//td[2]")).getText();
                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                ContactData contact = new ContactData(id, fname, lname, "title", "Address", "1", "March", "2000", "test1");
                contacts.add(contact);
            }
        return contacts;
        }
    }
