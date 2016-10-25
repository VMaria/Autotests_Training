package ru.stqa.training.addressbook;

import org.testng.annotations.Test;

import org.openqa.selenium.*;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        wd.findElement(By.linkText("groups")).click();
        wd.findElement(By.name("selected[]")).click();
        wd.findElement(By.name("delete")).click();
        wd.findElement(By.linkText("group page")).click();
    }

}