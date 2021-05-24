package framework;

import org.openqa.selenium.By;
import org.testng.Assert;



public class BaseForm {
    protected BaseElement baseElement;
    protected By formLocator;
    protected String formName;

    public BaseForm(By locator, String title) {
        baseElement = new BaseElement(locator);
        init(locator, title);
        assertIsOpen();

    }
public void init(By locator, String name){
        formLocator = locator;
        formName = name;
}

    public void assertIsOpen() {
        try{
            baseElement.waitElementBeVisible(formLocator);
            Assert.assertTrue(baseElement.getElement(formLocator).isDisplayed());
        } catch (Throwable e){
            System.out.println("Form " + formName + " is not opened");
        }
        }


}
