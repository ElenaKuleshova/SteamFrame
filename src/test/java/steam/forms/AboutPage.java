package steam.forms;

import framework.BaseForm;
import framework.ResourcePropertiesManager;
import framework.elements.Button;
import org.openqa.selenium.By;

public class AboutPage extends BaseForm{
    private static final String formName = AboutPage.class.getName();

    public AboutPage(){super(STEAM_LOGO, formName);}

    private static final By STEAM_LOGO = By.className("steam_logo");
    private final Button btnSteamInstallFile = new Button(By.xpath("//div[@id='about_greeting']//a[@class='about_install_steam_link']"));
    private static final String STEAM_INSTALL_FILE = new ResourcePropertiesManager("config.properties").getProperty("steamInstallFileName");

    public void downloadInstallFile(){
        btnSteamInstallFile.clickAndWait();
        baseElement.waitForFileCompleteDownload(STEAM_INSTALL_FILE);
    }


}
