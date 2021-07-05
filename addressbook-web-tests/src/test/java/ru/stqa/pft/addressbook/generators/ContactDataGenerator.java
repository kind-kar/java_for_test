package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch(ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContact(count);
        if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }


    private List<ContactData> generateContact(int count) {
        List<ContactData> contacts = new ArrayList<>();
        File photo = new File("src/test/resources/1556777145_1.jpg");
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("Test %s", i)).withLastName(String.format("Testov %s", i)).
                    withHome(String.format("111%s", i)).withMobile(String.format("7911111111%s", i)).withWork(String.format("890%s", i)).
                    withEmail(String.format("test%s@mail.ru", i)).withEmailTwo(String.format("test%s@example.ru", i)).
                    withEmailThree(String.format("test%s@gmail.ru", i)).withAddress(String.format("city S, street %s", i)).withPhoto(photo).
                    withGroup(String.format("test %s", i)));
        }
        return contacts;
    }
}
