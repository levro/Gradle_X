import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.browserPosition;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class DifBrowsersTest {

    private final static Logger log = LogManager.getLogger(UI_tests.class);

/*    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();*/

    @BeforeMethod
    public static void openKSMob() {
        log.info("--------> Set up webDriver");
        timeout = 10000;
        baseUrl = "https://app-ksmobile.ssstest.com";
        startMaximized = false;
        browserSize = "1024x720";
        browserPosition = "100x10";
    }

    @AfterMethod
    public static void closeWebDrv() {
        closeWebDriver();
    }

    @Test
    public void testChrome() {
        browser = "chrome";
        System.setProperty("selenide.browser", "chrome");
        log.info("--------> Open url: " + baseUrl);
        open("/");
        testloginScreen();
    }

    @Test
    public void testFirefox() {
        browser = "firefox";
        System.setProperty("selenide.browser", "firefox");
        log.info("--------> Open url: " + baseUrl);
        open("/");
        testloginScreen();
    }

    @Test (enabled = false)
    public void testIE() {
        browser = "ie";
        System.setProperty("selenide.browser", "ie");
        log.info("--------> Open url: " + baseUrl);
        open("/");
        testloginScreen();
    }

    @Test
    public void testEdge() {
        browser = "edge";
        System.setProperty("selenide.browser", "edge");
        log.info("--------> Open url: " + baseUrl);
        open("/");
        testloginScreen();
    }

    private void testloginScreen() {
        $(By.id("login")).shouldBe(visible);
        $(By.id("password")).shouldBe(visible);
        $(By.className("acceptTermsAndPolicy")).shouldHave(text("\n" +
                "                    By clicking \"Login\" button, you " +
                "confirm that you have read and accepted the Key Survey "));
    }
}
