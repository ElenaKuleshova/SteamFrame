package framework;


import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class Browser {

    private static final String  IMPLICIT_WAIT = "implicitWait";
    private static final String CONDITION_TIMEOUT = "conditionTimeout";
    private static final String PAGE_LOAD_TIMEOUT = "pageLoadTimeout";
    private static final String PROPERTIES_FILE = "config.properties";
    private static final String BROWSER_PROP = "browser";

    private static Browser instance;
    private static WebDriver driver;
    private static ResourcePropertiesManager resources;

    private static String implicitWait;
    private static String timeoutForPageLoad;
    private static String timeoutForCondition;
    private static String currentBrowser;

    public static Browser getInstance() {
        if (instance == null) {
            initProperties();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.setUp(currentBrowser);
            driver.manage().timeouts().implicitlyWait(Long.parseLong(implicitWait), TimeUnit.SECONDS);
            instance = new Browser();
        }
        return instance;
    }

    public void closeBrowser() {
        driver.quit();
        instance = null;
    }

    public static String getTimeoutForCondition() {
        return timeoutForCondition;
    }
    public static String getTimeoutForPageLoad() {
        return timeoutForPageLoad;
    }
    public static String getCurrentBrowser(){return currentBrowser;}

    private static void initProperties() {

        resources = new ResourcePropertiesManager(PROPERTIES_FILE);
        implicitWait= resources.getProperty(IMPLICIT_WAIT);
        timeoutForPageLoad = resources.getProperty(PAGE_LOAD_TIMEOUT);
        timeoutForCondition = resources.getProperty(CONDITION_TIMEOUT);
        currentBrowser = resources.getProperty(BROWSER_PROP);
    }

    public void navigate(String url) {
        driver.get(url);
    }
    public static WebDriver getDriver() {
        return driver;
    }
    public void windowMaximize() {
        driver.manage().window().maximize();
    }


}







