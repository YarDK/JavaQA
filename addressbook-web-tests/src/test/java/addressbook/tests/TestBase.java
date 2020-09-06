package addressbook.tests;

import addressbook.appmanager.ApplicationManager;
import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;


public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", "Chrome"));

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.info(String.format("Start test '%s' with parameters %s", m.getName(), Arrays.asList(p)));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m){
        logger.info(String.format("Stop test '%s'", m.getName()));
    }

    public void verifyGroupListInUI() {
        if(Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();

            MatcherAssert.assertThat(uiGroups, CoreMatchers.equalTo(dbGroups
                    .stream()
                    .map(g -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();

            System.out.println("dbContacts\n" + dbContacts + "\n");
            System.out.println("uiContacts\n" + uiContacts + "\n");

            MatcherAssert.assertThat(uiContacts, CoreMatchers.equalTo(dbContacts
            .stream()
            .map(g -> new ContactData()
                    .withFirst_name(g.getFirst_name())
                    .withLast_name(g.getLast_name())
                    .withId(g.getId())
                    .withAddress(g.getAddress()))
            .collect(Collectors.toSet())));
        }
    }
}
