package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    gotoAddNewContactPage();
    fillContactData(new Contact("Test", "Test", "Am Helmholtzring\n4d", "kind-kar@mail.ru", "71111111111"));
    submitContactCreation();
    returnToHomePage();
    logout();
  }
}
