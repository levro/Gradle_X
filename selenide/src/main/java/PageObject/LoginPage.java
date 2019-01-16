package PageObject;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getAndCheckWebDriver;

public class LoginPage extends BasePage{

    private static By loginField = By.id( "login");
    private static By passwordField = By.id( "password");
    private static By loginButton = By.id( "loginButton");

    public LoginPage() {
        open( "/" );
        webDriver = getAndCheckWebDriver();
    }

    public MainPage login() {
        $(loginField).setValue( "tomep1");
        $(passwordField).setValue("AutomationQA1");
        $(loginButton).click();
        sleep( 500 );
        if (isAlertPresent()) {
            confirm();
        }
        $(By.id("treeRoot")).waitUntil( Condition.visible, 10000 );
        return new MainPage();

    }
}
