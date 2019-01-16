package PageObject;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import static com.codeborne.selenide.Configuration.*;

public class BasePage {

    private static final String CONFIG_FILE = "config.properties";
    protected static WebDriver webDriver;

    public BasePage() {
        Properties properties = getPropertiesFromConfigFile();
        browser = properties.getProperty( "browser" );
        System.setProperty( "selenide.browser", browser );
        timeout = Long.parseLong( properties.getProperty( "timeout" ) );
        baseUrl = properties.getProperty( "baseUrl" );
        startMaximized = false;
        browserPosition = "10x10";
        browserSize = "1024x720";
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    private Properties getPropertiesFromConfigFile() {
        FileInputStream inputStream = null;
        try {
            URL resource = BasePage.class.getClassLoader().getResource( CONFIG_FILE );
            String info = resource.getPath();
            inputStream = new FileInputStream( info );
        } catch ( FileNotFoundException e ) {
            System.out.println( e );
            return null;
        }
        Properties prop = new Properties();
        try {
            prop.load( inputStream );
        } catch ( IOException e ) {
            System.out.println( e );
        }
        return prop;
    }

    static boolean isAlertPresent() {
        try {
            webDriver.switchTo().alert();
            return true;
        } catch ( Exception e ) {
            return false;
        }
    }
}
