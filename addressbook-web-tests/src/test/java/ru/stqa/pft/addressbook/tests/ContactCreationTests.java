package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
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

    int max = 0;
    for (ContactData c: after) {
      if (c.getId() > max) {
        max = c.getId();
      }
    }

    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    app.logout();
  }
}
