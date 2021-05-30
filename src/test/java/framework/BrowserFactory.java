package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

public class BrowserFactory {

    private static ResourcePropertiesManager resources;
    private static final String PROPERTIES_FILE = "config.properties";
    private static final String BROWSER_LANGUAGE = "locale";
    private WebDriver driver = null;
    private DriverManagerType driverManagerType;

    private static String fileDownloadDir = System.getProperty("user.dir")+ File.separator + "externalFiles" + File.separator + "downloadFiles";
    public static String getFileDownloadDir() {return fileDownloadDir;}

    public WebDriver setUp(String browserName) {
        resources = new ResourcePropertiesManager(PROPERTIES_FILE);

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
                options.addArguments("start-maximized");

                HashMap<String, Object> chromePref = new HashMap<>();
                chromePref.put("intl.accept_languages", resources.getProperty(BROWSER_LANGUAGE));
                chromePref.put("safebrowsing.enabled", true);
                chromePref.put("profile.default_content_settings.popups", 0);
                chromePref.put("download.default_directory", fileDownloadDir);
                options.setExperimentalOption("prefs", chromePref);

                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                options.merge(capabilities);


                driverManagerType = DriverManagerType.CHROME;
                WebDriverManager.getInstance(driverManagerType).setup();
                driver = new ChromeDriver(options);

                break;
             case "firefox":
                 FirefoxOptions firefoxOptions = new FirefoxOptions();
                 FirefoxProfile firefoxProfile = new FirefoxProfile();
                 firefoxProfile.setPreference("browser.download.folderList",2);
                 firefoxProfile.setPreference("intl.accept_languages", resources.getProperty(BROWSER_LANGUAGE));
                 firefoxProfile.setPreference("browser.download.dir", fileDownloadDir);
                 firefoxProfile.setPreference("browser.helperApps.neverAsk.openFile", "application/octet-stream");
                 firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
                 firefoxOptions.setProfile(firefoxProfile);
            driverManagerType = DriverManagerType.FIREFOX;
            WebDriverManager.getInstance(driverManagerType).setup();
            driver = new FirefoxDriver(firefoxOptions);
            break;
            default:
                System.out.println("Browser " + browserName + " is not supported");
                break;
       }
        return driver;  }
}
