package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import utils.TestNGListener;

import java.time.LocalDate;

@Listeners(TestNGListener.class)

public class SearchTests extends AppManager {
    HomePage homePage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void openHomePage() {
        homePage = new HomePage(getDriver());
    }

    @Test
    public void searchCarPositiveTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(7);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        softAssert.assertTrue(homePage.isMessageErrorCityDisplayed(), " City is required ");
        softAssert.assertFalse(homePage.isYallaButtonEnabled());
        softAssert.assertAll();

    }

    @Test
    public void searchCarInvalidEndDateNegativeTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().minusDays(7);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        softAssert.assertTrue(homePage.isMessageFirstInvalidDateDisplayed(),
                " You can't pick date before today ");
        softAssert.assertTrue(homePage.isMessageSecondInvalidDateDisplayed(),
                " Second date must be after first date ");
        softAssert.assertTrue(homePage.isMessageThirdInvalidDateDisplayed(),
                " You can't book car for less than a day ");
        softAssert.assertAll();

    }

    @Test
    public void searchCarEmptyDateNegativeTest() {
        String city = "Haifa";
        homePage.typeCityOnly(city);
        homePage.clickPopUpBtnOk();
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isErrorMessagePresent(),
                "Ожидалось сообщение об ошибке, но сайт его НЕ показал — это баг");


    }

}
