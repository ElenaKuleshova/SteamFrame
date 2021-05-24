package steam.forms;

import framework.BaseForm;
import org.openqa.selenium.By;

public class Header extends BaseForm {

    private static final By LOGO = By.className("logo");

    private static final String formName = Header.class.getName();
    private static final By LANGUAGE_PULLDOWN = By.id("language_pulldown");
    private final String languageOption = "//div[@id='language_dropdown']//a[contains(text(),'%s')]";

    private static final By INSTALL_BUTTON = By.xpath("//a[@class='header_installsteam_btn_content']");

    public Header() {
        super(LOGO,formName );
           }


    public void clickInstallButton(){
        baseElement.clickElement(INSTALL_BUTTON);

    }
    public void selectLanguage(String languageValue){
        baseElement.clickElement(LANGUAGE_PULLDOWN);
        try {
            baseElement.clickAndWait(By.xpath(String.format(languageOption, languageValue)));
        } catch (Exception e){
            System.out.println("Preferred language is already set");
            baseElement.clickElement(LANGUAGE_PULLDOWN);
        }
}


}
