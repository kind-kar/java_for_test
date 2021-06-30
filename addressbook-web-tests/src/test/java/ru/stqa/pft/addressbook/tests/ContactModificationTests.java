package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("Test").withLastName("Test1").
                    withAddress("City").withEmail("example@mail.com").withGroup("test1"), true);
            app.returnToHomePage();
        }
    }

    @Test
    public void contactModificationTests() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        app.contact().initContactModification(modifiedContact.getId());
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Test").withLastName("Test1");
        app.contact().fillContactData((contact), false);
        app.contact().submitContactModification();
        app.returnToHomePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
