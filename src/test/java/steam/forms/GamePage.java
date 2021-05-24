package steam.forms;

import framework.BaseForm;
import org.openqa.selenium.By;

public class GamePage extends BaseForm {
    private static final String formName = GamePage.class.getName();

    private static final By GAME_DISCOUNT = By.xpath("//div[@class='game_purchase_action']//div[@class='discount_pct']");


    public GamePage() {super (GAME_DISCOUNT, formName);}

    public boolean isGameDiscountSatisfiesSelectedDiscount(int givenDiscount){
        return baseElement.isElementContainsGivenValue(GAME_DISCOUNT, givenDiscount);

    }



}
