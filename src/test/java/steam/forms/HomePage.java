package steam.forms;

import framework.BaseForm;
import framework.elements.Label;
import org.openqa.selenium.By;

    public class HomePage extends BaseForm {
    private static final By HOME_CONTENT = By.className("home_page_content");
    private static final String formName = HomePage.class.getName();

    private static final String pulldownMenuItem = "//a[@class='pulldown_desktop'][contains(text(), '%s')]";
    private static final String popupMenuItem= "//a[@class='popup_menu_item'][contains(text(),'%s')]";

    public HomePage() {
        super(HOME_CONTENT,formName );
    }

    public void hoverMenuItem(String menuItem){
        Label lblMenuItem = new Label(By.xpath(String.format(pulldownMenuItem, menuItem)));
        lblMenuItem.moveMouse();
    }

    public void navigateMenuSubItem(String menuItem, String subMenuItem){
        hoverMenuItem(menuItem);
        Label lblMenuSubItem = new Label(By.xpath(String.format(popupMenuItem,subMenuItem)));
        lblMenuSubItem.clickAndWait();
    }
}
