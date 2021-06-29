package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    List<ContactData> before = app.contact().list();
    app.goTo().contactPage();
    ContactData contact = new ContactData("Test", "Test",
            "Am Helmholtzring\n4d", "kind-kar@mail.ru", "71111111111", "test2");
    app.contact().create(contact, true);
    app.returnToHomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);
    contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
}
