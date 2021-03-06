package ru.stqa.training.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.training.addressbook.model.GroupData;
import ru.stqa.training.addressbook.model.Groups;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {

        super(wd);
    }

    public void returnToGroupPage() {

        click(By.linkText("group page"));
    }

    public void goToGroupPage() {

        click(By.linkText("groups"));
    }

    public void submitGroupCreation() {

        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {

        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {

        click(By.name("new"));
    }

    public void deleteSelectedGroup() {

        click(By.name("delete"));
    }

    private void selectGroupById(int id) {

        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupModification() {

        click(By.name("edit"));
    }

    public void submitGroupModification() {

        click(By.name("update"));
    }

    public void create(GroupData group) {

        goToGroupPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroup();
        groupCache = null;
        returnToGroupPage();
    }

    public boolean isThereAGroup() {

        return isElementPresent(By.cssSelector(".group"));
    }

    public int count() {
        return wd.findElements(By.className("group")).size();
        //return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
            System.out.println(name);
            System.out.println(new GroupData().withId(id).withName(name));
        }

        return groups;
    }

    private Groups groupCache = null;

    public Groups all() throws IOException {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return groupCache;
    }
}

