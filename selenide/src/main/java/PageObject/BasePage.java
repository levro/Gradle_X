package PageObject;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.WebDriverRunner.getAndCheckWebDriver;

public class BasePage {

    protected static WebDriver webDriver;

    public BasePage() {
        timeout = 15000;
        baseUrl = "https://app-ksmobile.ssstest.com";
        startMaximized = false;
        browserPosition = "10x10";
        browserSize = "1024x720";
    }

    public WebDriver getWebDriver() {
        return getAndCheckWebDriver();
    }

/*    static boolean isAlertPresent() {
        try {
            webDriver = getAndCheckWebDriver();
            webDriver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }*/

    static void sleep( int mills ) {
        try {
            TimeUnit.MILLISECONDS.sleep( mills);
        } catch ( InterruptedException e ) {
            System.out.println( e );
        }
    }
}
