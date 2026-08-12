package ui_tests;

import dto.User1;
import manager.AppManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import utils.UserFactory;
import static utils.UserFactory.*;
import static utils.PropertiesReader.*;

import java.util.Random;

public class AuthorizationTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert= new SoftAssert();


    @BeforeMethod
    public void goToLoginPage(){
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }
    @Test
    public void authorizationPositiveTest(){
//        User1 user = positiveUser();
//        System.out.println(user);
       // int i = new Random().nextInt(1000); если меняются данные
        User1 user = User1.builder()
                .username(getProperty("base.properties","email"))
                .password(getProperty("base.properties","password"))
                .build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
    }
    @Test
    public void loginNegativeTests() {
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isEmailAndPasswordFilled());

    }
    @Test
    public void loginNegativeTest2(){
        int i = new Random().nextInt(1000);
        User1 user = User1.builder()
                .username("victor19802610@gmail.com")
                .password(getProperty("base.properties","password"))
                .build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        //Assert.assertTrue(loginPage.isLoginFailedAlertPresent());
    }
    @Test
    public void loginPositiveTest(){
        User1 user = User1.builder()
                .username("rima_simonova370@gmail.com")
                .password("SSas124!")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        //Assert.assertTrue(loginPage.ispPopUpSuccessLoginDisplayed());
    }
    @Test
    public void loginNegativeEmptyAllFieldsWOClickInFieldsTest(){
        loginPage.clickBtnYalla();
        Assert.assertFalse(loginPage.isBtnYallaEnabled());

    }
    @Test
    public void loginNegativeEmptyAllFieldsWithClickInFieldsTest(){
        User1 user = User1.builder()
                .username("")
                .password("")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertFalse(loginPage.isBtnYallaEnabled(),"validate isBtnYallaEnabled ");
        System.out.println("test working");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"),"validate message: Email is required");

        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"),"validate message: Password is required");
        softAssert.assertAll();

    }

    }



