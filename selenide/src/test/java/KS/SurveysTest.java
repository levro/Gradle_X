package KS;

import PageObject.LoginPage;
import PageObject.MainPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class SurveysTest extends Assert {

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
    public void testCreateAndDeleteFolder() {
        String folderName = "Selenide";
        MainPage mainPage = loginPage.login();
        mainPage.createFolder( folderName );
        assertTrue( mainPage.isFolderPresent( folderName ), "Folder [" + folderName + "] is not present" );
        mainPage.deleteFolderByName( folderName );
        assertFalse( mainPage.isFolderPresent( folderName ), "Folder [" + folderName + "] should not present" );
    }

    @Test
    public void testRenameFolder() {
        String folderName = "Selenide";
        MainPage mainPage = loginPage.login();
        mainPage.createFolder( folderName );
        String newFolderName = "Arsenide";
        mainPage.renameFolder( folderName, newFolderName );
        assertTrue( mainPage.isFolderPresent( newFolderName ), "Folder [" + newFolderName + "] is not present" );
        mainPage.deleteFolderByName( newFolderName );
    }
}
