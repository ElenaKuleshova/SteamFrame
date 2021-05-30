package steam.forms;

import framework.BaseForm;
import framework.elements.Label;
import framework.elements.Select;
import org.openqa.selenium.By;

public class GenrePage extends BaseForm {
    private static final By GENRE_PAGE_TITLE = By.className("pageheader");
    private static final String formName = GenrePage.class.getName();
    private final Label lblRecommendedGameDiscounts = new Label(By.xpath("//div[@id='specials_container']//div[@class='discount_pct']"));
    private final String RECOMMENDED_GAME_WITH_SPECIFIED_DISCOUNT = "//div[@id='specials_container']//div[@class='discount_pct'][contains(text(), '%s')]";
    private static Select sltYearAtAgePage = new Select(By.xpath("//select[@id='ageYear']"));

    private Integer maxDiscount = 0;
    public Integer getMaxDiscount(){return maxDiscount;}

    public GenrePage() {
        super(GENRE_PAGE_TITLE,formName );
    }

    public GamePage navigateRandomRecommendedGameWithMaxDiscount(){
       maxDiscount = lblRecommendedGameDiscounts.getMaxIntValueFromElementsList();
       Label lblRecommendedGamesWithMaxDiscount = new Label(By.xpath(String.format(RECOMMENDED_GAME_WITH_SPECIFIED_DISCOUNT, maxDiscount)));
       lblRecommendedGamesWithMaxDiscount.selectRandomElementFromList();
       if (isAgePageOpened()){
           AgeCheckPage ageCheckPage = new AgeCheckPage();
           ageCheckPage.inputValidYear();
           ageCheckPage.clickViewPageButton();
        }
       return new GamePage();
    }

public boolean isAgePageOpened(){
return sltYearAtAgePage.isElementPresentedOnPage();
        }
}
