package ui_tests;

import dto.Car;
import dto.User1;
import enums.TypesOfFuel;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWork;
import pages.LoginPage;
import pages.PopUpPage;
import utils.CarFactory;
import utils.enums.HeaderMenu;
import static utils.CarFactory.*;
import static utils.PropertiesReader.getProperty;


public class LetTheCarWorkTests extends AppManager {

    LoginPage loginPage;
    LetTheCarWork letTheCarWork;

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

//        User1 user = User1.builder().location("Rishon Lizion").build();
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
//    }
        // Homework Negative Tests
// 1. only click btn Submit
// 2. click all fields and btnSubmit
// 3. leave one field blank and other fields type with valid data
// 4. wrong year
    }
}