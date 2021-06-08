package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.gotoAddNewContactPage();
    app.fillContactData(new Contact("Test", "Test", "Am Helmholtzring\n4d", "kind-kar@mail.ru", "71111111111"));
    app.submitContactCreation();
    app.returnToHomePage();
    app.logout();
  }
}
