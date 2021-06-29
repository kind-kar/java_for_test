package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Set<ContactData> before = app.contact().all();
    app.goTo().contactPage();
    ContactData contact = new ContactData().withFirstName("Test").withLastName("Test1").
            withAddress("City").withEmail("example@mail.com");
    app.contact().create(contact, true);
    app.returnToHomePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    contact.withId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
    before.add(contact);
    Assert.assertEquals(after, before);
  }
}
