import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.text.Highlighter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.addListener;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class SelTest {

    public WebDriver driver;


    @BeforeClass
    public static void openKsMobile() {
        timeout = 10000;
        baseUrl = "https://app-ksmobile.ssstest.com";
        startMaximized = false;
        browser = "firefox";
        System.setProperty("selenide.browser", "firefox");
        browserPosition = "10x10";
        browserSize = "1024x960";
        //addListener( new Highlighter());

        open("/");

    }

    @AfterClass
    public static void logout() {
        closeWebDriver();
    }

    @Test
    public void logggin() {
        $( By.id( "login")).setValue( "tomep1");
        $( By.id( "password")).setValue( "AutomationQA1");
        $(By.id( "loginButton" )).click();
        confirm();
        $(By.id( "treeRoot" )).shouldHave( text( "Surveys" ) );
        $(By.id( "folderNameLabel" )).shouldHave( text( "Main" ) );
        $(By.linkText( "Logout" )).click();
        confirm();
    }
}
