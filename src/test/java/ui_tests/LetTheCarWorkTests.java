package ui_tests;

import data_providers.CarDataProvider;
import dto.Car;
import dto.User1;
import enums.TypesOfFuel;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LetTheCarWork;
import pages.LoginPage;
import pages.PopUpPage;
import utils.CarFactory;
import utils.TestNGListener;
import utils.enums.HeaderMenu;

import java.time.LocalDate;

import static utils.CarFactory.*;

import static utils.PropertiesReader.getProperty;
@Listeners(TestNGListener.class)


public class LetTheCarWorkTests extends AppManager {

    LoginPage loginPage;
    LetTheCarWork letTheCarWork;
    SoftAssert softAssert = new SoftAssert();

    User1 user;

    @BeforeMethod
    public void goToLetTheCarWorkPage() {
        User1 user = User1.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
        loginPage = new HomePage(getDriver())
                .clickHeaderButtons(HeaderMenu.LOGIN);
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        loginPage.clickBtnOk();
//        loginPage.clickBtnLetCarWork();
        letTheCarWork = new HomePage(getDriver()).clickHeaderButtons(HeaderMenu.LET_THE_CAR_WORK);


    }

    @Test
    public void typeLetTheCarWorkTest() {
//        Car car = CarFactory.positiveCar();
        Car car = positiveCar();

        System.out.println(car);
        letTheCarWork.typeAddNewCarForm(car);
        letTheCarWork.downLoadImage("img.png");
        letTheCarWork.clickBtnSubmitWithJS();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("{\"city\":\"must not be blank\"}"));


//      User1 user = User1.builder().location("Rishon Lizion").build();
//        letTheCarWork.typeAddress(user);
//        letTheCarWork.clickBtnPopOk();
//        Car car = Car.builder()
//                .manufacture("Kia")
//                .model("Sorento")
//                .year("2023")
//                .fuel_types(new String[]{TypesOfFuel.FUEL.getTypes()[0]})
//                .seats("5")
//                .carClass("Suv")
//                .number("123-45-678")
//                .price("85000")
//                .textAbout("Хорошее состояни.Один владелец." +
//                        "Регулярное обслуживание").build();
//        letTheCarWork.typeDetailsCar(car);
//        letTheCarWork.clickBtnSubmitWithJS();
//        Assert.assertTrue(letTheCarWork.isPopUpCarAddingFailedDisplayed());
//
    }


    // Homework Negative Tests
// 1. only click btn Submit
// 2. click all fields and btnSubmit
// 3. leave one field blank and other fields type with valid data
// 4. wrong year


    @Test
    public void clickBtnSubmitNegativeTest() {
        letTheCarWork.clickBtnSubmitWithJS();
        softAssert.assertTrue(letTheCarWork.isPopUpCarAddingFailedDisplayed(),
                "validate message: PopUpCarAddingFailedDisplayed");
        softAssert.assertTrue(letTheCarWork.isPopUpTextMessageDisplayed(),
                "validate message:PopUpTextMessageDisplayed");
        softAssert.assertAll();

    }

    @Test
    public void clickAllFieldsBtnSubmitWithJSNegativeTest() {
        letTheCarWork.clickAddNewCarForm();
        letTheCarWork.clickBtnSubmitWithJS();
        softAssert.assertTrue(letTheCarWork.isPopUpCarAddingFailedDisplayed(),
                "validate message: PopUpCarAddingFailedDisplayed");
        softAssert.assertTrue(letTheCarWork.isPopUpTextMessageDisplayed(),
                "validate message:PopUpTextMessageDisplayed");
        softAssert.assertAll();

    }

    @Test
    public void clickAllFieldsBtnSubmitWithoutJSNegativeTest() {
        letTheCarWork.clickAddNewCarForm();
        letTheCarWork.clickBtnSubmit();
        softAssert.assertTrue(letTheCarWork.isWrongAddressMessageDisplayed());
        softAssert.assertTrue(letTheCarWork.isMakeRequiredMessageDisplayed());
        softAssert.assertTrue(letTheCarWork.isModelRequiredMessageDisplayed());
        softAssert.assertTrue(letTheCarWork.isYearRequiredMessageDisplayed());
        softAssert.assertTrue(letTheCarWork.isFuelRequiredMessageDisplayed());
        softAssert.assertTrue(letTheCarWork.isSeatsRequiredMessageDisplayed());
        softAssert.assertTrue(letTheCarWork.isCarClassRequiredMessageDisplayed());
        softAssert.assertTrue(letTheCarWork.isNumberRequiredMessageDisplayed());
        softAssert.assertTrue(letTheCarWork.isPriceRequiredMessageDisplayed());
        softAssert.assertTrue(letTheCarWork.isAboutMessageDisplayed());
        softAssert.assertAll();
    }

    @Test
    public void typeLetTheCarWorkNegativeOneFieldEmptyClickBtnSubmitWithJSTest() {
        Car car = positiveCar();
        System.out.println(car);
        letTheCarWork.typeAddNewCarFormWithoutModelType(car);
        letTheCarWork.downLoadImage("img.png");
        letTheCarWork.clickBtnSubmitWithJS();
        softAssert.assertTrue(letTheCarWork.isModelRequiredMessageDisplayed());
        softAssert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("{\"city\":\"must not be blank\",\"model\":\"must not be blank\"}"));
        softAssert.assertAll();
    }


    @Test
    public void typeLetTheCarWorkNegativeOneFieldEmptyClickBtnSubmitWithoutJSTest() {
        Car car = positiveCar();
        System.out.println(car);
        letTheCarWork.typeAddNewCarFormWithoutModelType(car);
        letTheCarWork.downLoadImage("img.png");
        letTheCarWork.clickBtnSubmit();
        Assert.assertTrue(letTheCarWork.isModelRequiredMessageDisplayed());
    }

    @Test(dataProvider = "dataProviderForTypeLetTheCarWorkWrongYear", dataProviderClass = CarDataProvider.class)
    public void typeLetTheCarWorkWrongYearTest(Car car) {
        letTheCarWork.typeAddNewCarForm(car);
        letTheCarWork.downLoadImage("img.png");
        letTheCarWork.clickBtnSubmitWithJS();

    }

    @Test(dataProvider = "dataProviderForTypeLetTheCarWorkWrongYear", dataProviderClass = CarDataProvider.class)
    public void typeLetTheCarWorkWrongYearAndClickBtnSubmitWithoutJSTest(Car car) {
        letTheCarWork.typeAddNewCarForm(car);
        letTheCarWork.downLoadImage("img.png");
        letTheCarWork.clickBtnSubmit();

    }

    @Test
    public void typeLetTheCarWorkNegativeWrongYearTest() {
//        Car car = CarFactory.positiveCar();
        Car car = positiveCar();
        car.setYear(String.valueOf(LocalDate.now().getYear() + 1));
        System.out.println(car);
        letTheCarWork.typeAddNewCarForm(car);
        letTheCarWork.downLoadImage("img.png");
        Assert.assertTrue(letTheCarWork.isTextInErrorPresent("Wrong year"));

        //letTheCarWork.clickBtnSubmitWithJS();
        //Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("{\"city\":\"must not be blank\"}"));


    }

    @Test
    public void typeLetTheCarWorkNegativeWrongYearNotDigitTest() {

        Car car = positiveCar();
        car.setYear("a");
        car.setYear(String.valueOf(LocalDate.now().getYear() + 1));
        System.out.println(car);
        letTheCarWork.typeAddNewCarForm(car);
        letTheCarWork.downLoadImage("img.png");
        Assert.assertTrue(letTheCarWork.isTextInErrorPresent("Year required"));

    }
}