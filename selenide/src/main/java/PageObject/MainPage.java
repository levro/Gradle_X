package PageObject;


import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {

    private static By mainTree = By.id("treeRoot");

    public MainPage() {
        //this.webDriver = webDriver;
    }

    public boolean isMainTreeDisplayed() {
        return $(mainTree).isDisplayed();
    }
}
