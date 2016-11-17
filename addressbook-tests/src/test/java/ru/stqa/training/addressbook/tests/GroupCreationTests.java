package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        app.goTo().groupPage();
        app.group().isThereAGroup();
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("test1");
        app.group().create(group);
        app.goTo().groupPage();
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        //Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        //before.sort(byId);
        //after.sort(byId);
        System.out.println(after);
        System.out.println(before);
        Assert.assertEquals(before, after);
    }
}
