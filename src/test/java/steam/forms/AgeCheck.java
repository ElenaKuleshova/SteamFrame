package steam.forms;

import framework.BaseForm;
import org.openqa.selenium.By;

import java.util.Calendar;

public class AgeCheck extends BaseForm {

    private static final By YEAR_LOCATOR = By.xpath("//select[@id='ageYear']");
    private static final By BTN_VIEW_PAGE = By.xpath("//a[@onclick='ViewProductPage()']");
    private static final String formName = AgeCheck.class.getName();

    public AgeCheck() {
        super(YEAR_LOCATOR,formName );
    }

    public void inputValidYear() {
        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Integer validYear = currentYear - 19;

        baseElement.sendKeys(YEAR_LOCATOR, validYear.toString());

    }

    public void clickViewPageButton() {
          baseElement.clickAndWait(BTN_VIEW_PAGE);
    }
}
