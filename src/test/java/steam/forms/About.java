package steam.forms;

import framework.BaseForm;
import org.openqa.selenium.By;

public class About extends BaseForm{
    private static final String formName = About.class.getName();

    public About(){super(STEAM_LOGO, formName);}


    private static final By STEAM_LOGO = By.className("steam_logo");
    private static final By INSTALL_STEAM_BTN = By.xpath("//div[@id='about_greeting']//a[@class='about_install_steam_link']");
    private static final String STEAM_INSTALL_FILE = "SteamSetup";

    public void downloadInstallFile(){

        baseElement.clickAndWait(INSTALL_STEAM_BTN);
        baseElement.waitForFileToDownload();
    }

    public Boolean isInstallFileDownloaded(){
       return  baseElement.isDownloadDirectoryContainsFile(STEAM_INSTALL_FILE);
    }

}
