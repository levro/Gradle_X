package KS;

import PageObject.BasePage;
import PageObject.LoginPage;
import PageObject.MainPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class SurveysTest {

    private LoginPage loginPage;

    @BeforeClass
    public void beforeClass() {
        //webDriver = new BasePage().getWebDriver();
        loginPage = new LoginPage();
    }

    @Test
    public void testLogin() {
        open( "/" );
        MainPage mainPage = loginPage.login();
        assert (mainPage.isMainTreeDisplayed());
    }
}
