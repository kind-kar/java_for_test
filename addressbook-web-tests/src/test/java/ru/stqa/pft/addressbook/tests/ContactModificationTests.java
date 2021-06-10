package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void contactModificationTests() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactData(new ContactData("Test", "Test", "Am Helmholtzring\n4d", "kind-kar@mail.ru", "71111111111"));
        app.getContactHelper().submitContactModification();
        app.returnToHomePage();
    }

}
