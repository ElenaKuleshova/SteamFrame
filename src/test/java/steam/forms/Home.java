package steam.forms;

import framework.BaseForm;
import org.openqa.selenium.By;

    public class Home extends BaseForm {
    private static final By HOME_CONTENT = By.className("home_page_content");
    private static final String formName = Home.class.getName();

    private static final String pulldownMenuItem = "//a[@class='pulldown_desktop'][contains(text(), '%s')]";
    private static final String popupMenuItem= "//a[@class='popup_menu_item'][contains(text(),'%s')]";
    private final String tabMenuItem = "//a[@class='tab  '][contains(.,'%s')]";



    public Home() {
        super(HOME_CONTENT,formName );
    }


    public void hoverMenuItem(String menuItem){
        baseElement.moveMouse(By.xpath(String.format(pulldownMenuItem, menuItem)));
    }


    public void navigateMenuSubItem(String menuItem, String subMenuItem){
        hoverMenuItem(menuItem);

        baseElement.clickElement(By.xpath(String.format(popupMenuItem,subMenuItem)));
    }

}
