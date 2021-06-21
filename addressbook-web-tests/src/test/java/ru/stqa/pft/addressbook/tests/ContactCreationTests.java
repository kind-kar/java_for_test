package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().gotoAddNewContactPage();
    app.getContactHelper().fillContactData(new ContactData("Test", "Test",
            "Am Helmholtzring\n4d", "kind-kar@mail.ru", "71111111111", "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.returnToHomePage();
//    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
    app.logout();
  }
}
