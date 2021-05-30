package steam.forms;

import framework.BaseForm;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class Header extends BaseForm {

    private static final By LOGO = By.className("logo");
    private static final String formName = Header.class.getName();
    private final Label languagePulldown = new Label(By.id("language_pulldown"));
    private final String languageSingleSelect = "//div[@id='language_dropdown']//a[contains(text(),'%s')]";
    private final Button btnInstallSteam = new Button(By.xpath("//a[@class='header_installsteam_btn_content']"));

    public Header() {
        super(LOGO,formName );
           }

    public void clickInstallButton(){
       btnInstallSteam.clickElement();
    }

    public void selectLanguage(String languageValue){
        languagePulldown.clickElement();
        try {
        Label lblLanguageOption = new Label(By.xpath(String.format(languageSingleSelect, languageValue)));
        lblLanguageOption.clickAndWait();

        } catch (Exception e){
            System.out.println("Preferred language is already set");
            languagePulldown.clickElement();
        }
}
}
