package PageObject;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {

    private static By mainTree = By.id("treeRoot");
    private static By addNewFolder = By.id( "newFolder" );
    private static By newFolderName = By.id( "newFolderName" );
    private static By submitNewFolderName = By.xpath( "/html/body/ks-fmt:setbundle/div[1]/div/div[2]/div[2]/div[1]/div[2]/ul/li[2]/ul[1]/li/div/button[1]" );

    public MainPage() {
        //this.webDriver = webDriver;
    }

    public boolean isMainTreeDisplayed() {
        return $(mainTree).isDisplayed();
    }

    public void createFolder( String folderName ) {
        $(addNewFolder).click();
        $(newFolderName).setValue( folderName ).pressEnter();
    }
}
