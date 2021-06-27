package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void testContactCreation() {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoAddNewContactPage();
    ContactData contact = new ContactData("Test", "Test",
            "Am Helmholtzring\n4d", "kind-kar@mail.ru", "71111111111", "test1");
    app.getContactHelper().fillContactData(contact, true);
    app.getContactHelper().submitContactCreation();
    app.returnToHomePage();
//    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
    app.logout();
  }
}
