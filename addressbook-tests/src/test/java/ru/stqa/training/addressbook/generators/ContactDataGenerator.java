package ru.stqa.training.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.training.addressbook.model.ContactData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-d", description = "Data Format")
    public String dataformat;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public Properties properties = new Properties();

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (dataformat.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (dataformat.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if (dataformat.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + dataformat);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;\n", contact.getFname(), contact.getLname(), contact.getAddress()));
            }
        }
    }

    private List<ContactData> generateContacts(int count) throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFname(String.format(properties.getProperty("contactfname") + i)).
                    withLname(String.format(properties.getProperty("contactlname") + i)).
                    withAddress(String.format(properties.getProperty("contactaddress") + i)).
                    withHomephone(String.format(properties.getProperty("contacthomephone") + i)).
                    withMobilephone(String.format(properties.getProperty("contactmobilephone") + i)).
                    withWorkphone(String.format(properties.getProperty("contactworkphone") + i)).
                    withEmail(String.format(properties.getProperty("contactemail") + i)).
                    withEmail2(String.format(properties.getProperty("contactemail2") + i)).
                    withEmail3(String.format(properties.getProperty("contactemail3") + i)).
                    withDay(String.format(properties.getProperty("contactday") + i)).
                    withMonth(String.format(properties.getProperty("contactmonth") + i)).
                    withYear(String.format(properties.getProperty("contactyear") + i)));
        }
        System.out.println(contacts);
        return contacts;
    }
}