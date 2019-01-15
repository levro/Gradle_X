package PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getAndCheckWebDriver;

public class LoginPage extends BasePage{

    private static By loginField = By.id( "login");
    private static By passwordField = By.id( "password");
    private static By loginButton = By.id( "loginButton");

    public LoginPage() {
        //super( webDriver );
    }

    public MainPage login() {
        $(loginField).setValue( "tomep1");
        $(passwordField).setValue("AutomationQA1");
        $(loginButton).click();
        sleep( 1000 );
        confirm();
/*        if (isAlertPresent()) {
            confirm();
        }*/
        sleep( 8000 );
        return new MainPage();

    }

    static boolean isAlertPresent() {
        try {
            webDriver = getAndCheckWebDriver();
            webDriver.switchTo().alert();
            return true;
        } catch ( Exception e ) {
            return false;
        }
    }
}
