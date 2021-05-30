package steam.forms;

import framework.BaseForm;
import framework.elements.Button;
import framework.elements.Select;
import org.openqa.selenium.By;

import java.util.Calendar;

public class AgeCheckPage extends BaseForm {

    private static final By YEAR_LOCATOR = By.xpath("//select[@id='ageYear']");
    private static final String formName = AgeCheckPage.class.getName();

    private static final Select sltYearAtAgePage  = new Select(By.xpath("//select[@id='ageYear']"));
    private final Button btnViewPage = new Button(By.xpath("//a[@onclick='ViewProductPage()']"));


    public AgeCheckPage() {
        super(YEAR_LOCATOR,formName );
    }

    public void inputValidYear() {
        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Integer validYear = currentYear - 19;
        sltYearAtAgePage.sendKeys(validYear.toString());
           }

    public void clickViewPageButton() {
          btnViewPage.clickAndWait();
    }
}
