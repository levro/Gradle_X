package ks;

import pageObject.LoginPage;
import pageObject.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class SurveysTest extends Assert {

    private LoginPage loginPage;
    private MainPage mainPage;

    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage();
    }

    @AfterMethod
    public void closeWebDrv() {
        mainPage.logout();
        closeWebDriver();
    }

    @Test
    public void testLogin() {
        mainPage = loginPage.login();
        assert (mainPage.isMainTreeDisplayed());
    }

    @Test
    public void testCreateAndDeleteFolder() {
        String folderName = "Selenide";
        mainPage = loginPage.login();
        mainPage.createFolder( folderName );
        assertTrue( mainPage.isFolderPresent( folderName ), "Folder [" + folderName + "] is not present" );
        mainPage.deleteFolderByName( folderName );
        assertFalse( mainPage.isFolderPresent( folderName ), "Folder [" + folderName + "] should not present" );
    }

    @Test
    public void testRenameFolder() {
        String folderName = "Selenide";
        mainPage = loginPage.login();
        mainPage.createFolder( folderName );
        String newFolderName = "Arsenide";
        mainPage.renameFolder( folderName, newFolderName );
        assertTrue( mainPage.isFolderPresent( newFolderName ), "Folder [" + newFolderName + "] is not present" );
        mainPage.deleteFolderByName( newFolderName );
    }
}
