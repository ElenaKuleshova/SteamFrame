package framework.elements;

import framework.Browser;
import framework.BrowserFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.util.List;
import java.util.Set;


import static framework.Browser.getTimeoutForPageLoad;
import static framework.Browser.getTimeoutForCondition;

public class BaseElement {
    protected static Browser browser = Browser.getInstance();
    protected WebElement webElement;
    protected List<WebElement> webElements;
    protected By locator;
    protected Actions actions = new Actions(browser.getDriver());

    public BaseElement(By locator) {
        this.locator = locator;
            }

    public WebElement getElement() {
        return new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()))
                .until(driver -> driver.findElement(locator));
    }

    public WebElement getElementByLocator(By locator) {
        return new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()))
                .until(driver -> driver.findElement(locator));
    }

    public List<WebElement> getElements(By locator) {
        return new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()))
                .until(driver -> driver.findElements(locator));
    }

    public void moveMouse() {
        waitElementBeVisible();
        actions.moveToElement(getElement()).perform();
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getElement());
        }
    }

    public void waitInvisibilityText(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()));
        wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
    }

    public void clickElement() {
        waitElementBeClickable();
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getElement());
        }
        getElement().click();
    }

    public void clickAndWait() {
        clickElement();
        waitForPageToLoad();
    }

    public void waitElementBeClickable() {
       WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()));
        wait.until(ExpectedConditions.elementToBeClickable(getElement()));
    }

    public void waitElementBeVisible() {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()));
        wait.until(ExpectedConditions.visibilityOf(getElement()));
    }
    public void waitElementBePresent(By locator){
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(),  Long.parseLong(getTimeoutForCondition()));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public String getText(By locator) {
        waitElementBeClickable();
        return getElement().getText();
    }
    public void sendKeys(String key)
    {
        waitElementBeClickable();
        getElement().sendKeys(key);
    }

    public Integer getMaxIntValueFromElementsList() {
    List<WebElement> elements = getElements(locator);
    int maxIntValue = 0;
    for (int i = 0; i < elements.size(); i++) {
        String value = elements.get(i).getText();
        value = value.substring(1, value.length() - 1);
        int intValue = Integer.parseInt(value);
        if (intValue > maxIntValue) {
            maxIntValue = intValue;
        }
    }
    return maxIntValue;
    }

    public void selectElementBySpecifiedText(By locator,Integer intValue){
    String text = intValue.toString();
    List<WebElement> elements = getElements(locator);
    for (WebElement element : elements){
        String elementText = element.getText();
        if (elementText.contains(text)){
            element.click();
            break;
        }
    }
    }

    public void selectRandomElementFromList(){
        List<WebElement> elements = getElements(locator);
        if (elements.size()>1){
        int random = (int) (Math.random()*(elements.size()-1));
        elements.get(random).click();}
        else {elements.get(0).click();}

    }

    public boolean isElementContainsGivenValue(int givenValue){
        String elementText = getElement().getText();
        elementText = elementText.substring(1,elementText.length()-1);
        return Integer.parseInt(elementText) == givenValue;
    }

    public boolean isElementPresentedOnPage(){
        boolean elementPresence = true;
        try {
            getElementByLocator(locator);
        } catch (Exception e){
            elementPresence = false;
        }
    return elementPresence;
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(browser.getDriver(), Long.parseLong(getTimeoutForPageLoad()));
        wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver d) {
                Object result = ((JavascriptExecutor) d)
                        .executeScript("return document['readyState'] ? 'complete' == document.readyState : true");

                if (result != null && result instanceof Boolean && (Boolean) result) {
                    return true;
                }
                return false;
            }
        });
    }

    public void waitForDownloadProgressComplete() {
        WebDriver driver = browser.getDriver();

        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(getTimeoutForPageLoad()));
        wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver driver) {
                String parentWindow = driver.getWindowHandle();
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("window.open()");
                Set<String> allWindowHandles = driver.getWindowHandles();
                for (String winHandle : allWindowHandles) {
                    if (!winHandle.equals(parentWindow)) {
                        driver.switchTo().window(winHandle);
                    }
                }
                JavascriptExecutor downloadWindowExecutor = (JavascriptExecutor) driver;
                double percentageProgress = (double) 0;
                switch (browser.getCurrentBrowser()) {
                    case "chrome":

                        driver.get("chrome://downloads");
                        try {
                            while (percentageProgress != 100) {

                                percentageProgress = (Long) downloadWindowExecutor.executeScript("return document.querySelector('downloads-manager')." +
                                        "shadowRoot.querySelector('#downloadsList downloads-item')." +
                                        "shadowRoot.querySelector('#progress').value");

                            }
                            return true;
                        } catch (JavascriptException exception) {
                            return true;
                        }
                    case "firefox":
                        driver.get("about:downloads");

                        try {
                            while (percentageProgress != 100) {
                                percentageProgress = (Long) downloadWindowExecutor.executeScript("return document.querySelector('downloads-manager')." +
                                        "shadowRoot.querySelector('#downloadsList downloads-item')." +
                                        "shadowRoot.querySelector('#progress').value");

                            }
                            return true;
                        } catch (JavascriptException exception) {
                            return true;
                        }
                }
                return false;
            }
        });
    }

    public void waitForFileConvertFromTempExtension(String filename){
        WebDriver driver = browser.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(getTimeoutForPageLoad()));
        wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver driver) {
                String fileDownloadDir = BrowserFactory.getFileDownloadDir();
                File directory = new File (fileDownloadDir);
                File[] files = directory.listFiles();

                for (File file : files) {
                    if (file.getName().contains(filename)) {
                        return true;
                    }
                }
                return false;
            }
            });
    }

public void waitForFileCompleteDownload(String filename){
    waitForDownloadProgressComplete();
    waitForFileConvertFromTempExtension(filename);
}

    public Boolean isDownloadDirectoryContainsFile(String fileName){
        String fileDownloadDir = BrowserFactory.getFileDownloadDir();
        File directory = new File (fileDownloadDir);
        File[] files = directory.listFiles();
        Boolean filePresence = false;
        for (File file : files) {
            if (file.getName().contains(fileName)) {
               filePresence = true;
            }
        }
return filePresence;
    }

}
