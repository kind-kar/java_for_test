package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.contact().isTheAContact()) {
            app.goTo().gotoAddNewContactPage();
            app.contact().createContact(new ContactData("Test", "Test",
                    "Am Helmholtzring\n4d", "kind-kar@mail.ru", "71111111111", "test2"), true);
            app.returnToHomePage();
        }
        List<ContactData> before = app.contact().list();
        app.contact().selectContact(before.size() - 1);
        app.contact().deleteSelectedContact();
        app.contact().submitAlert();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(after, before);
    }

}
