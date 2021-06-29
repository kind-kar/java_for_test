package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("Test").withLastName("Test1").
                    withAddress("City").withEmail("example@mail.com").withGroup("test1"), true);
            app.returnToHomePage();
        }
    }

    @Test
    public void contactModificationTests() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        app.contact().initContactModification(modifiedContact.getId());
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Test").withLastName("Test1");
        app.contact().fillContactData((contact), false);
        app.contact().submitContactModification();
        app.returnToHomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(after, before);
    }
}
