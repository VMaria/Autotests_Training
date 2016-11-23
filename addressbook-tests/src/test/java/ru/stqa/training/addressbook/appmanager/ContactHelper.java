package ru.stqa.training.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.training.addressbook.model.ContactData;
import ru.stqa.training.addressbook.model.Contacts;

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
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("work"), contactData.getWorkphone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
//        select(By.name("bday"), contactData.getDay());
//        select(By.name("bmonth"), contactData.getMonth());
//        type(By.name("byear"), contactData.getYear());
//        attach(By.name("photo"), contactData.getPhoto());
//        if (creation) {
//            select(By.name("new_group"), contactData.getGroup());
//        } else {

//            Assert.assertFalse(isElementPresent(By.name("new_group")));
//        }
   }

    public void initContactCreation() {

        click(By.linkText("add new"));
    }

    public void initContactModification(int id) {

        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    private void initContactView(int id) {
        wd.findElement(By.cssSelector("a[href='view.php?id=" + id + "']")).click();

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
        contactCache = null;
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
        closeDialogWindow();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String fname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String homephone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workphone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()). withFname(fname).withLname(lname).
                withAddress(address).withHomephone(homephone).withMobilephone(mobilephone).
                withWorkphone(workphone).withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public ContactData infoFromViewForm(ContactData contact) {
        initContactView(contact.getId());
        String info[] = wd.findElement(By.id("content")).getText().replaceAll("[-():]", "").replaceAll("[MWH]", "")
                .replaceAll("\\n+\\s*", "\n").replaceFirst(" ", "\n").split("\n");
        String email = wd.findElement(By.cssSelector("a[href='mailto:email']")).getText();
        String email2 = wd.findElement(By.cssSelector("a[href='mailto:email2']")).getText();
        String email3 = wd.findElement(By.cssSelector("a[href='mailto:email3']")).getText();
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFname(info[0]).withLname(info[1]).
                withAddress(info[2]).withHomephone(info[3]).withMobilephone(info[4]).withWorkphone(info[5]).
                withEmail(email).withEmail2(email2).withEmail3(email3);
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

    private Contacts contactCache = null;

    public Contacts all() {

        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String fname = element.findElement(By.xpath(".//td[3]")).getText();
            String lname = element.findElement(By.xpath(".//td[2]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            contactCache.add(new ContactData().withId(id).withFname(fname).withLname(lname).withTitle("title").
              withAddress(address).withAllPhones(allPhones).
              withAllEmails(allEmails).
              withDay("5").withMonth("January").withYear("2000").withGroup("test1"));
            String[] phones = element.findElement(By.xpath(".//td[6]")).getText().split("\n");
            contactCache.add(new ContactData().withId(id).withFname(fname).withLname(lname).withAddress("Address").
                    withHomephone(phones[0]).withMobilephone(phones[1]).withWorkphone(phones[2]).
                    withEmail("email").withEmail2("email2").withEmail3("email3"));
                  //  withDay("5").withMonth("January").withYear("2000").withGroup("test1"));
        }
        return contactCache;
    }

    public int count() {

        return wd.findElements(By.name("selected[]")).size();
    }
}
