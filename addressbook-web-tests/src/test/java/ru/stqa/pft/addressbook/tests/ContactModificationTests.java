package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void contactModificationTests() {
        if (! app.getContactHelper().isTheAContact()) {
            app.getNavigationHelper().gotoAddNewContactPage();
            app.getContactHelper().createContact(new ContactData("Test", "Test",
                    null, null, null, "test1"), true);
            app.returnToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Test", "Test", null, null, null, "test1");
        app.getContactHelper().fillContactData((contact), false);
        app.getContactHelper().submitContactModification();
        app.returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    }

}
