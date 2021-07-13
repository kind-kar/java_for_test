package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("Test").withLastName("Test1").
                    withAddress("City").withEmail("example@mail.com").withGroup("test 1").withHome("791111111111").withMobile("1234").
                    withWork("45678").withPhoto(new File("src/test/resources/1556777145_1.jpg")), true);
            app.returnToHomePage();
        }
    }

    @Test
    public void contactModificationTests() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        app.contact().initContactModification(modifiedContact.getId());
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Test").withLastName("Test1").
                withAddress("City").withEmail("example@mail.com").withEmailTwo("example2@mail.com").withEmailThree("example3@mail.com").withHome("791111111111").withMobile("1234").
                withWork("45678").withPhoto(new File("src/test/resources/1556777145_1.jpg"));
        app.contact().fillContactData((contact), false);
        app.contact().submitContactModification();
        app.returnToHomePage();
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
