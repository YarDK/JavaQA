package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd;
    private String browser;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private DbHelper dbhelper;

    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException{
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        //BrowserType не поддерживает chrome и firefox по умолчанию в имеющейся бибилиотеке

        dbhelper = new DbHelper(); // Инициализация помощника БД

        if(browser.toLowerCase().equals("chrome")){
            System.setProperty("webdriver.chrome.driver","src\\test\\resources\\browserdrivers\\chromedriver.exe");
            wd = new ChromeDriver();
        } else if(browser.toLowerCase().equals("firefox")) {
            System.setProperty("webdriver.gecko.driver","src\\test\\resources\\browserdrivers\\geckodriver.exe");
            wd = new FirefoxDriver();
        } else {
            System.out.println("unknown browser");
        }
        // Исполняемый драйвер расположил в пакете приложения по соответствующему пути


        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    private void logout() {
      wd.findElement(By.linkText("Logout")).click();
    }

    public void stop() {
      logout();
      wd.quit();
    }


    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public DbHelper db() {
        return dbhelper;
    }
}
