package ru.stqa.training.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.training.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void submitContactCreation() {

        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {

        type(By.name("firstname"),contactData.getFname());
        type(By.name("lastname"),contactData.getLname());
        type(By.name("title"),contactData.getTitle());
        type(By.name("address"),contactData.getAddress());
        click(By.xpath(contactData.getDay()));
        click(By.xpath(contactData.getMonth()));
        type(By.name("byear"),contactData.getYear());
    }

    public void initContactCreation() {

        click(By.linkText("add new"));
    }
    public void initContactModification() {

        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {

        click(By.name("update"));
    }

    public void selectContact() {

        click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }

    public void deleteSelectedContact() {

        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void closeDialogWindow () {

        wd.switchTo().alert().accept();
    }
}
