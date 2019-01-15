package KS;

import PageObject.LoginPage;
import PageObject.MainPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class SurveysTest {

    private LoginPage loginPage;

    @BeforeClass
    public void beforeClass() {
        loginPage = new LoginPage();
    }

    @AfterClass
    public static void closeWebDrv() {
        closeWebDriver();
    }

    @Test
    public void testLogin() {
        MainPage mainPage = loginPage.login();
        assert (mainPage.isMainTreeDisplayed());
    }

    @Test
    public void testCreateFolder() {
        MainPage mainPage = loginPage.login();
        mainPage.createFolder( "Selenide" );
    }
}
