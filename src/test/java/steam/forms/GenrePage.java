package steam.forms;

import framework.BaseForm;
import org.openqa.selenium.By;

public class GenrePage extends BaseForm {
    private static final By GENRE_PAGE_TITLE = By.className("pageheader");
    private static final String formName = GenrePage.class.getName();

    private final By RECOMMENDED_GAME_DISCOUNT = By.xpath("//div[@id='specials_container']//div[@class='discount_pct']");
    private final By AGE_PAGE_LOCATOR = By.id("app_agegate");

    private Integer maxDiscount = 0;
    public Integer getMaxDiscount(){return maxDiscount;}

    public GenrePage() {
        super(GENRE_PAGE_TITLE,formName );
    }

    public GamePage navigateRecommendedGameWithMaxDiscount(){
       maxDiscount = baseElement.getMaxIntValueFromElementsList(RECOMMENDED_GAME_DISCOUNT);
       baseElement.selectElementBySpecifiedText(RECOMMENDED_GAME_DISCOUNT,maxDiscount);
       if (isAgePageOpened()){
           AgeCheck ageCheckPage = new AgeCheck();
           ageCheckPage.inputValidYear();
           ageCheckPage.clickViewPageButton();
        }
       return new GamePage();
    }

public boolean isAgePageOpened(){
return baseElement.isElementPresentedOnPage(AGE_PAGE_LOCATOR);
}
}
