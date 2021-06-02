package steam.forms;

import framework.BaseForm;
import framework.LanguagePropertiesReader;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class Header extends BaseForm {

    private static final By LOGO = By.className("logo");
    private static final String formName = Header.class.getName();
    private final Label lblLanguagePulldown = new Label(By.id("language_pulldown"));
    private final String languageSingleSelect = "//div[@id='language_dropdown']//a[contains(text(),'%s')]";
    private final Button btnInstallSteam = new Button(By.xpath("//a[@class='header_installsteam_btn_content']"));
    protected LanguagePropertiesReader languagePropertiesReader;
    private Label lblLanguageOption;

    public Header() {
        super(LOGO,formName );
           }

    public void clickInstallButton(){
       btnInstallSteam.clickElement();
    }

    public boolean isLanguageChangeNeeded(String preferredSteamLanguage){
        String currentLangugagePulldownText = lblLanguagePulldown.getText();
        languagePropertiesReader = new LanguagePropertiesReader(preferredSteamLanguage);
        String preferredLanguagePulldownText = languagePropertiesReader.getLangProperty("header.languagePulldown");
        lblLanguagePulldown.clickElement();
        lblLanguageOption = new Label(By.xpath(String.format(languageSingleSelect, preferredSteamLanguage)));
        if (currentLangugagePulldownText.equals(preferredLanguagePulldownText) && !lblLanguageOption.isElementPresentedOnPage()){
            return false;
        } else return true;
    }

    public void selectLanguage(String preferredSteamLanguage){
        if (isLanguageChangeNeeded(preferredSteamLanguage)){
          lblLanguageOption.clickAndWait();
        } else lblLanguagePulldown.clickElement();
}
}
