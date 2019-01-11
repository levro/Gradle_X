import org.junit.*;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.addListener;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import javax.swing.text.Highlighter;

public class UI_tests {

    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    @BeforeClass
    public static void openInbox() {
        timeout = 10000;
        baseUrl = "https://app-ksmobile.ssstest.com";
        startMaximized = false;
        browser = "firefox";
        System.setProperty("selenide.browser", "firefox");
        browserPosition = "10x10";
        browserSize = "1024x720";

        open("/");
        login();
    }

    @AfterClass
    public static void logout() {
        $(By.linkText( "Logout" )).click();
        confirm();
        closeWebDriver();
    }

    private static void login() {
        $( By.id( "login")).setValue( "tomep1");
        $( By.id( "password")).setValue( "AutomationQA1");
        $(By.id( "loginButton" )).click();
        confirm();
        $(By.id( "treeRoot" )).shouldBe();
    }

    @Test
    public void testSurveyLabels() {
        $(By.name( "LABEL_SURVEY" )).click();
        $(By.id( "treeRoot" )).shouldBe();
        $(By.id( "treeRoot" )).shouldHave( text( "Surveys" ) );
        $(By.id( "folderNameLabel" )).shouldHave( text( "Main" ) );
    }

    @Test
    public void testContactsLabels() {
        $(By.name( "LABEL_CONTACTS" )).click();
        $(By.id( "divAddContact" )).shouldBe();
        //$(By.id( "treeViewNode_1_0_0_null" )).shouldHave( text( "Contact Managers" ) );
        $(By.name( "AddContact" )).shouldHave( attribute("title", "Add Contact") );
    }
}
