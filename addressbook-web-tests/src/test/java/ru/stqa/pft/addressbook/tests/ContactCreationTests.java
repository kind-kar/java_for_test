package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoAddNewContactPage();
    app.getContactHelper().fillContactData(new ContactData("Test", "Test", "Am Helmholtzring\n4d", "kind-kar@mail.ru", "71111111111"));
    app.getContactHelper().submitContactCreation();
    app.returnToHomePage();
    app.logout();
  }
}
