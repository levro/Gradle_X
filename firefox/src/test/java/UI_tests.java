import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.confirm;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class UI_tests {

    private static WebDriver webDriver;
    private final static Logger log = LogManager.getLogger(UI_tests.class);

/*    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();*/

    @BeforeTest
    @Parameters({"browser", "browserPosition"})
    public static void openKSMob() {
        log.info("--------> Set up webDriver");
        timeout = 10000;
        baseUrl = "https://app-ksmobile.ssstest.com";
        startMaximized = false;
        browserSize = "1024x720";
        browserPosition = "100x10";
        browser = "firefox";
        System.setProperty("selenide.browser", "firefox");
        //webDriver = getAndCheckWebDriver();
/*        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.firefox.marionette", "src\\test\\resources\\webdriver\\geckodriver.exe");
            open("/");
            //webDriver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            browser = "firefox";
            System.setProperty("webdriver.firefox.marionette", "src\\test\\resources\\webdriver\\geckodriver.exe");
            //System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\webdriver\\chromedriver.exe");
            open("/");
            //webDriver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("ei")) {
            System.setProperty("webdriver.edge.driver", "src\\test\\resources\\webdriver\\MicrosoftWebDriver.exe");
            open("/");
            //webDriver = new InternetExplorerDriver();
        }*/
        log.info("--------> Open url: " + baseUrl);

        open("/");
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
    public void testSurveyLabels() {
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
    public void testContactsLabels() {
        login();
        log.info("--------> Go to CONTACTS menu");
        $(By.name("LABEL_CONTACTS")).click();
        $(By.id("divAddContact")).shouldBe();
        $(By.name("AddContact")).shouldHave(attribute("title", "Add Contact"));
        $(By.linkText("Logout")).click();
        confirm();
    }

    private static void login() {
        $(By.id("login")).setValue("tomep1");
        $(By.id("password")).setValue("AutomationQA1");
        $(By.id("loginButton")).click();
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
