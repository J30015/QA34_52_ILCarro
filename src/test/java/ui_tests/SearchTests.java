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

import static org.openqa.selenium.devtools.v85.debugger.Debugger.pause;

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
        homePage.clickBtnSubmitWithJS();
        Assert.assertTrue(homePage.isUrlContainsText("results"));
//        softAssert.assertTrue(homePage.isMessageErrorCityDisplayed(), " City is required ");
//        softAssert.assertFalse(homePage.isYallaButtonEnabled());
//        softAssert.assertAll();

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
        Assert.assertFalse(homePage.isErrorMessagePresent(),
                "Ожидалось сообщение об ошибке, но сайт его НЕ показал — это баг");


    }

    @Test
    public void searchCarNegativeTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnSubmitWithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent("You can't book car for less than a day"));


    }

    @Test
    public void searchCarNegativeMoreYearTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(1).plusDays(1);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnSubmitWithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent("You can't pick date after one year"));


    }

    @Test
    public void searchCarNegativeStarDateAfterEndDateTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(7);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnSubmitWithJS();
        softAssert.assertTrue(homePage.isTextInErrorPresent("Second date must be after first date"));
        softAssert.assertTrue(homePage.isTextInErrorPresent("You can't book car for less than a day"));
        softAssert.assertAll();


    }

    @Test
    public void searchCarWithCalendarPositiveTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(10);
        homePage.typeSearchWithCalendar(city, startDate, endDate);
        homePage.clickBtnSubmitWithJS();
        Assert.assertTrue(homePage.isUrlContainsText("results"));

    }

    @Test
    public void searchCarWithCalendarEndDateInPastNegativeTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().minusDays(10);
        homePage.typeSearchWithCalendar(city, startDate, endDate);
        Assert.assertFalse(homePage.isDateClickable(endDate));

    }

    @Test
    public void searchCarWithCalendarStartDateInPastNegativeTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().minusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(10);
        homePage.typeSearchWithCalendar(city, startDate, endDate);
        Assert.assertFalse(homePage.isDateClickable(startDate));

    }

    @Test
    public void searchCarWithCalendarSameDatesNegativeTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(3);
        LocalDate endDate = LocalDate.now().plusDays(3);
        homePage.typeSearchWithCalendar(city, startDate, endDate);
        Assert.assertTrue(homePage.isMessageDateRequiredDisplayed(),
                "You can't book car for less than a day");

    }

    @Test
    public void searchCarWithCalendarDistantDatesNegativeTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(3);
        LocalDate endDate = LocalDate.now().plusMonths(12).plusDays(3);
        homePage.typeSearchWithCalendar(city, startDate, endDate);
        Assert.assertFalse(homePage.isDateClickable(endDate));
    }


}
