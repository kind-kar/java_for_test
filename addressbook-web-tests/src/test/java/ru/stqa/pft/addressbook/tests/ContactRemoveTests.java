package ru.stqa.pft.addressbook.tests;

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

public class ContactRemoveTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1").withName("test 2").withFooter("test 3"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("Test").withLastName("Test1").
                    withAddress("City").withEmail("example@mail.com").withHome("791111111111").withMobile("1234").
                    withWork("45678").withPhoto(new File("src/test/resources/1556777145_1.jpg")), true);
            app.returnToHomePage();
        }
    }

    @Test
    public void testContactRemoveFromGroup() {
        Groups allGroups = app.db().groups();
        Contacts allContacts = app.db().contacts();
        Contacts contactsForRemoving = new Contacts();
        GroupData selectedGroup;
        ContactData deletedContactFromGroup;
        for (ContactData contactData : allContacts) {
            Groups groups = contactData.getGroups();
            if (groups.size() >= 1) {
                contactsForRemoving.add(contactData);
            }
        }
        if (contactsForRemoving.size() == 0) {
            ContactData addContactInGroup = allContacts.iterator().next();
            selectedGroup = allGroups.iterator().next();
            app.contact().addInGroup(addContactInGroup, selectedGroup);
            app.goTo().gotoHomePage();
            contactsForRemoving.add(addContactInGroup.inGroup(selectedGroup));
        }
        deletedContactFromGroup = contactsForRemoving.iterator().next();
        selectedGroup = deletedContactFromGroup.getGroups().iterator().next();
        app.contact().selectGroupList(selectedGroup);
        app.contact().removeInGroup(deletedContactFromGroup, selectedGroup);
        app.goTo().gotoHomePage();
        app.contact().selectGroupList(selectedGroup);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), allContacts.size());
        for (ContactData contact : after) {
            if (contact.getId() == deletedContactFromGroup.getId()) {
                assertThat(deletedContactFromGroup.getGroups().without(selectedGroup), equalTo(contact.getGroups()));
            }
        }
        verifyContactListInUI();
    }
}
