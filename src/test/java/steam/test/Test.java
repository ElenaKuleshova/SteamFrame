package steam.test;


import framework.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import steam.forms.*;




public class Test extends BaseTest {

    @org.testng.annotations.Test
    public void downloadSteamInstallFile() {


        Header header = new Header();
        header.selectLanguage("English");

        Home home = new Home();
        home.navigateMenuSubItem("Categories", "Action");


       GenrePage genrePage = new GenrePage();
       GamePage gamePage = genrePage.navigateRecommendedGameWithMaxDiscount();

       SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(gamePage.isGameDiscountSatisfiesSelectedDiscount(genrePage.getMaxDiscount()));


       header.clickInstallButton();

       About about = new About();
       about.downloadInstallFile();
        softAssert.assertTrue(about.isInstallFileDownloaded());
        softAssert.assertAll();
    }

}




