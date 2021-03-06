package steam.test;


import framework.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steam.forms.*;


public class SteamTest extends BaseTest {


@Parameters({"preferredSteamLanguage"})
    @Test
    public void downloadSteamInstallFile(String preferredSteamLanguage) {
        Header header = new Header();
        header.selectLanguage(preferredSteamLanguage);
        HomePage homePage = new HomePage();
        homePage.navigateMenuSubItem(categoriesMenuItem, categoriesActionSubMenuItem);
       GenrePage genrePage = new GenrePage();
       GamePage gamePage = genrePage.navigateRandomRecommendedGameWithMaxDiscount();
       SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(gamePage.isGameDiscountSatisfiesSelectedDiscount(genrePage.getMaxDiscount()));
        header.clickInstallButton();
       AboutPage aboutPage = new AboutPage();
        aboutPage.downloadInstallFile();
        softAssert.assertAll();







    }

    }





