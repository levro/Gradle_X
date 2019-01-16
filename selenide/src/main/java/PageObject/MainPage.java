package PageObject;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage extends BasePage {

    private static By mainTree = By.id("treeRoot");
    private static By addNewFolder = By.id( "newFolder" );
    private static By newFolderName = By.id( "newFolderName" );

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

    public void selectFolderByName( String folderName ) {
        $$(By.xpath( "//*[@class=\"folder\"]" )).findBy( text( folderName ) ).toWebElement().click();
    }
}
