package addressbook.generators;

import addressbook.model.ContactData;
import addressbook.model.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact counter")
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
        } catch (ParameterException e){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            save_csv(contacts, new File(file));
        } else if(format.equals("xml")) {
            save_xml(contacts, new File(file));
        }else if(format.equals("json")){
            save_json(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format");
        }

    }

    private void save_json(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(json);
        bw.close();
    }

    private void save_xml(List<ContactData> contacts, File file) throws IOException{
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        String xml = xstream.toXML(contacts);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(xml);
        bw.close();
    }


    private void save_csv(List<ContactData> contacts, File file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (ContactData g : contacts) bw.write(String
                .format("%s;%s;%s\n", g.getFirst_name(), g.getLast_name(), g.getTelephone_home()));
        bw.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirst_name("First_name_" + (i + 1))
                    .withLast_name("Last_name_" + (i + 1))
                    .withMiddle_name("Middle_name_" + (i + 1))
                    .withNick_name("Nick_name_" + (i + 1))
                    .withAddress("Address_" + (i + 1))
                    .withTelephone_home("111111110" + (i + 1))
                    .withTelephone_mobile("22222220" + (i + 1))
                    .withTelephone_work("33333330" + (i + 1))
                    .withEmail_1(String.format("email_1_%s@mail.com", i + 1))
                    .withEmail_2(String.format("email_2_%s@mail.com", i + 1))
                    .withEmail_3(String.format("email_3_%s@mail.com", i + 1))
            );
        }
        return contacts;
    }
}
