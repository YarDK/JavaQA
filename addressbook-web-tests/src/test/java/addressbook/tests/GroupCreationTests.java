package addressbook.tests;

import addressbook.model.GroupData;

import addressbook.model.Groups;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroup_csv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("src/test/resources/groups.csv")))
        {
            String line = br.readLine();
            while (line != null) {
                String[] split = line.split(";");
                list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
                line = br.readLine();
            }
            return list.iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroup_xml() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("src/test/resources/groups.xml")))
        {
            String xml = "";
            String line = br.readLine();
            while (line != null) {
                xml += line;
                line = br.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
            return groups.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroup_json() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("src/test/resources/groups.json")))
        {
            String json = "";
            String line = br.readLine();
            while (line != null) {
                json += line;
                line = br.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType()); // List<GroupData>.class
            return groups.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroup_json")
    public void testGroupCreation(GroupData groupData) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(groupData);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(groupData.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    }

    @Test(enabled = false)
    public void testBadGroupCreation() {

        GroupData groupData = new GroupData().withName("New_group'"); // С апострофом группа не может быть создана
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(groupData);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }

}
