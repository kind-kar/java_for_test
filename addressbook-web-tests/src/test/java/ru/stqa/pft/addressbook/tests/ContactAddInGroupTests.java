package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactAddInGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("Test").withLastName("Test1").
                    withAddress("City").withEmail("example@mail.com").withHome("791111111111").withMobile("1234").
                    withWork("45678").withPhoto(new File("src/test/resources/1556777145_1.jpg")), true);
            app.returnToHomePage();
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withName("test2").withFooter("test3"));
        }
    }

    @Test
    public void testContactAddInGroup() {
        Groups allGroups = app.db().groups();
        Contacts allContacts = app.db().contacts();
        ContactData addedContactToGroup = allContacts.iterator().next();
//        ContactData contact = addedContactToGroup.inGroup(selectedGroup);
        app.contact().findGroupForAddi(addedContactToGroup, allGroups);
        GroupData selectedGroup = allGroups.iterator().next();
        if (app.contact().isTheContactInGroup(addedContactToGroup, selectedGroup)) {
//            app.contact().selectGroupList("[none]");
        } else {
            GroupData newGroup = new GroupData().withName("test1").withName("test2").withFooter("test3");
            app.goTo().groupPage();
            app.group().create(newGroup);
            Groups after = app.db().groups();
            selectedGroup = newGroup.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
            app.goTo().gotoHomePage();
        }
        app.contact().addInGroup(addedContactToGroup, selectedGroup);
        app.goTo().gotoHomePage();
        Contacts after = app.db().contacts();
//            assertEquals(after.size(), allContacts.size() - 1);
//            assertThat(after, equalTo(allContacts.without(addedContactToGroup).withAdded(contact)));
//            verifyContactListInUI();

    }
}
