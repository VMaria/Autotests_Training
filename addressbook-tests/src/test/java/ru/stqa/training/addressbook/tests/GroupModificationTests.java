package ru.stqa.training.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.GroupData;
import ru.stqa.training.addressbook.model.Groups;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupModificationTests extends TestBase {

    public Properties properties = new Properties();

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test"));
        }

        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test"));
        }
    }

    @Test
    public void testGroupModification() throws IOException {

        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName(properties.getProperty("groupname")).
                withHeader(properties.getProperty("groupheader")).withFooter(properties.getProperty("groupfooter"));
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
        verifyGroupListInUI();
    }
}