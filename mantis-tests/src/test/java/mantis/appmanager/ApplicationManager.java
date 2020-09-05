package mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd;
    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("/Users/yaroslavkorotyshov/Desktop/JavaQA/mantis-tests/src/test/resources/%s.properties", target))));

    }

    public void stop() {
        if(wd != null){
            wd.quit();
        }
    }

    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return  properties.getProperty(key);

    }

    public RegistrationHelper registration() {
        if(registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp(){
        if(ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public MailHelper mail(){
        if(mailHelper == null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public WebDriver getDriver() {
        if(wd == null){
            if (browser.toLowerCase().equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/Contents/MacOS/chromedriver");
                wd = new ChromeDriver();
            } else if (browser.toLowerCase().equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", "/Applications/Firefox.app/Contents/MacOS/geckodriver");
                wd = new FirefoxDriver();
            } else {
                System.out.println("unknown browser");
            }
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));

            //BrowserType не поддерживает chrome и firefox по умолчанию в имеющейся бибилиотеке
            // Исполняемый драйвер расположил в пакете приложения по соответствующему пути

        }
        return wd;
    }
}
