package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().isThereAGroup();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().createGroup(new GroupData("test", null, null));
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}
