package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.contact().isTheAContact()) {
            app.goTo().contactPage();
            app.contact().create(new ContactData("Test", "Test",
                    "Am Helmholtzring\n4d", "kind-kar@mail.ru", "71111111111", "test2"), true);
            app.returnToHomePage();
        }
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(index);
        Assert.assertEquals(after, before);
    }
}
