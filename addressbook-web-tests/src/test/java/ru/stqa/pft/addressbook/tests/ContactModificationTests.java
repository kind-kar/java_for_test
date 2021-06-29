package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void contactModificationTests() {
        if (! app.contact().isTheAContact()) {
            app.goTo().contactPage();
            app.contact().create(new ContactData("Test", "Test",
                    "Am Helmholtzring\n4d", "kind-kar@mail.ru", "71111111111", "test2"), true);
            app.returnToHomePage();
        }
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().initContactModification(index);
        ContactData contact = new ContactData(before.get(index).getId(),"Test", "Test", null,
                null, null, null);
        app.contact().fillContactData((contact), false);
        app.contact().submitContactModification();
        app.returnToHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}
