package pageObject;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPage extends BasePage {

    private static By mainTree = By.id( "treeRoot" );
    private static By addNewFolderButton = By.id( "newFolder" );
    private static By newFolderNameField = By.id( "newFolderName" );
    private static By surveyFolders = By.xpath( "//*[@class=\"folder\"]" );
    private static By deleteFolderButton = By.xpath( "//*[@id=\"deleteFolderD\"]" );
    private static By renameFolderButton = By.xpath( "//*[@id=\"renameFolder\"]" );

    public MainPage() {
    }

    public boolean isMainTreeDisplayed() {
        return $( mainTree ).isDisplayed();
    }

    public boolean isFolderPresent( String folderName ) {
        return $$( surveyFolders ).findBy( text( folderName ) ).exists();
    }

    public void createFolder( String folderName ) {
        $( addNewFolderButton ).click();
        $( newFolderNameField ).setValue( folderName ).pressEnter();
        sleep( 1000 );
    }

    public void selectFolderByName( String folderName ) {
        $$( surveyFolders ).findBy( text( folderName ) ).click();
    }

    public void deleteFolderByName( String folderName ) {
        selectFolderByName( folderName );
        $( deleteFolderButton ).click();
        confirm();
        sleep( 1000 );
    }

    public void renameFolder( String folderName, String newFolderName ) {
        selectFolderByName( folderName );
        $( renameFolderButton ).click();
        $( newFolderNameField ).setValue( newFolderName ).pressEnter();
        sleep( 1000 );
    }
}
