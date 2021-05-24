package framework;

import framework.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.util.List;


import static framework.Browser.getTimeoutForPageLoad;
import static framework.Browser.getTimeoutForCondition;

public class BaseElement {
    protected static Browser browser = Browser.getInstance();
    protected WebElement webElement;
    protected Actions actions = new Actions(browser.getDriver());

    public BaseElement(By by) {
        this.webElement = getElement(by);
    }


    public static WebElement getElement(By locator) {
        return new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()))
                .until(driver -> driver.findElement(locator));
    }


    public static List<WebElement> getElements(By locator) {
        return new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()))
                .until(driver -> driver.findElements(locator));
    }

    public void moveMouse(By locator) {
        waitElementBeVisible(locator);
        actions.moveToElement(getElement(locator)).perform();
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getElement(locator));
        }
    }

    public void waitInvisibilityText(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()));
        wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
    }

    public void clickElement(By locator) {
        waitElementBeClickable(locator);
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getElement(locator));
        }
        getElement(locator).click();
    }

    public void clickAndWait(By locator) {
        clickElement(locator);
        waitForPageToLoad();
    }

    public void waitElementBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitElementBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitElementBePresent(By locator){
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(),  Long.parseLong(getTimeoutForCondition()));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public String getText(By locator) {
        waitElementBeClickable(locator);
        return getElement(locator).getText();
    }
    public void sendKeys(By locator, String key)
    {
        waitElementBeClickable(locator);
    getElement(locator).sendKeys(key);
    }

    public Integer getMaxIntValueFromElementsList(By locator) {
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

    public boolean isElementContainsGivenValue(By locator, int givenValue){
        String elementText = getElement(locator).getText();
        elementText = elementText.substring(1,elementText.length()-1);
        return Integer.parseInt(elementText) == givenValue;
    }

    public boolean isElementPresentedOnPage(By locator){
        boolean elementPresence = true;
        try {
            getElement(locator);
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




    public void waitForFileToDownload() {
        WebDriverWait wait = new WebDriverWait(browser.getDriver(), Long.parseLong(getTimeoutForCondition()));
        wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver d) {
                String fileDownloadDir = BrowserFactory.getFileDownloadDir();
                File directory = new File (fileDownloadDir);
                File[] files = directory.listFiles();
                if (files.length > 0) {
                    System.out.println("Directory contains file");
                    return true;
                } else {
                System.out.println("Download directory is empty");
                return false;
            }

            }
        });
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
