package addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        if(text != null) {
            String existing_text = wd.findElement(locator).getAttribute("value");
            if(! text.equals(existing_text)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void acceptAlert(){
        wd.switchTo().alert().accept();
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    protected WebElement waitForElementPresent(By locator, String error_massage, int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(wd, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waiter(int millis){
        try{
            Thread.sleep(millis);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
