package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isTheAContact()) {
            app.getNavigationHelper().gotoAddNewContactPage();
            app.getContactHelper().createContact(new ContactData("Test", "Test",
                    "Am Helmholtzring\n4d", "kind-kar@mail.ru", "71111111111", "test1"), true);
            app.returnToHomePage();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().submitAlert();
    }

}
