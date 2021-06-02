package steam.forms;

import framework.BaseForm;
import framework.elements.Label;
import org.openqa.selenium.By;

public class GamePage extends BaseForm {
    protected Header header;
    private static final By GAME_DISCOUNT = By.xpath("//div[@class='game_purchase_action']//div[@class='discount_pct']");
    private static final String formName = GamePage.class.getName();
    private final Label lblGameDiscount = new Label(GAME_DISCOUNT);
    public GamePage() {super (GAME_DISCOUNT, formName);}

    public boolean isGameDiscountSatisfiesSelectedDiscount(int givenDiscount){
        return lblGameDiscount.isElementContainsGivenValue(givenDiscount);
    }
}
