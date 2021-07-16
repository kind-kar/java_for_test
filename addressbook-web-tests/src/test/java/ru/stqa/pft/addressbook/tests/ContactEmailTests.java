package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("Test").withLastName("Test1").
                    withAddress("Homeaddress 4, Sankt-Petersburg").withEmail("example@mail.com").
                    withEmailTwo("email2@mail.com").withEmailThree("email3@mail.com")
                    .withHome("123-4").withMobile("791111-111111").withWork("45678-"), true);
            app.returnToHomePage();
        }
    }

    @Test
    public void testContactEmail() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    public String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmailTwo(), contact.getEmailThree()).
                stream().filter((s) -> ! s.equals("")).
                collect(Collectors.joining("\n"));
    }
}
