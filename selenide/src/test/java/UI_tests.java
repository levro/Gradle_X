import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getAndCheckWebDriver;

public class UI_tests {

    private static WebDriver webDriver;
    private final static Logger log = LogManager.getLogger(UI_tests.class);

/*    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();*/

    @Parameters({"browser", "browserPosition"})
    @BeforeTest
    public static void openKSMob(String brow, String browPos) {
        log.info("--------> Set up webDriver");
        timeout = 15000;
        baseUrl = "https://app-ksmobile.ssstest.com";
        startMaximized = false;
        browserPosition = browPos;
        browserSize = "1024x720";
        if (brow.equalsIgnoreCase("firefox")) {
            browser = "firefox";
            System.setProperty("selenide.browser", "firefox");
        } else if (brow.equalsIgnoreCase("chrome")) {
            browser = "chrome";
            System.setProperty("selenide.browser", "chrome");
        } else if (brow.equalsIgnoreCase("ei")) {
            browser = "ie";
            System.setProperty("selenide.browser", "ie");
        } else if (brow.equalsIgnoreCase("edge")) {
            browser = "edge";
            System.setProperty("selenide.browser", "edge");
        }
        log.info("--------> Open url: " + baseUrl);

        open("/");
        webDriver = getAndCheckWebDriver();
    }

    @AfterTest
    public static void closeWebDrv() {
        closeWebDriver();
    }

    @Test
    public void testloginScreen() {
        $(By.id("login")).shouldBe(visible);
        $(By.id("password")).shouldBe(visible);
        $(By.className("acceptTermsAndPolicy")).shouldHave(text("\n" +
                "                    By clicking \"Login\" button, you " +
                "confirm that you have read and accepted the Key Survey "));
    }

    @Test
    public void testSurveyLabels() throws InterruptedException {
        login();
        log.info("--------> Go to SURVEY menu");
        $(By.name("LABEL_SURVEY")).click();
        $(By.id("treeRoot")).shouldBe();
        $(By.id("treeRoot")).shouldHave(text("Surveys"));
        $(By.id("folderNameLabel")).shouldHave(text("Main"));
        $(By.linkText("Logout")).click();
        confirm();
    }

    @Test
    public void testContactsLabels() throws InterruptedException {
        login();
        log.info("--------> Go to CONTACTS menu");
        $(By.name("LABEL_CONTACTS")).click();
        $(By.id("divAddContact")).shouldBe();
        $(By.name("AddContact")).shouldHave(attribute("title", "Add Contact"));
        $(By.linkText("Logout")).click();
        confirm();
    }

    private static void login() throws InterruptedException {
        $(By.id("login")).setValue("tomep1");
        $(By.id("password")).setValue("AutomationQA1");
        $(By.id("loginButton")).click();
        TimeUnit.SECONDS.sleep(1);
        if (isAlertPresent()) {
            confirm();
        }
        $(By.id("treeRoot")).shouldBe();
    }

    private static boolean isAlertPresent() {
        try {
            webDriver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
