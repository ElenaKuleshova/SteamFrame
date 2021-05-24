package framework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;


public class BaseTest {

public static WebDriver driver;
protected static Browser browser = Browser.getInstance();
public static ResourcePropertiesManager resources;
private static final String PROPERTIES_FILE = "config.properties";

private static final String BASE_URL = "url";


   @BeforeTest

    public static void initializeWebdriver(){
        resources = new ResourcePropertiesManager(PROPERTIES_FILE);

        browser.getInstance();
        browser.navigate(resources.getProperty(BASE_URL));
    }
    @BeforeTest
    public static void cleanDownloadDir(){
       String fileDownloadDir = BrowserFactory.getFileDownloadDir();
        File directory = new File (fileDownloadDir);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (!file.delete()) {
                System.out.println("Failed to delete" + file);
            }
        }

    }

    @AfterTest
   public static void tearDown(){
       browser.closeBrowser();
   }


}
