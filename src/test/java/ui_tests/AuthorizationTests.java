package ui_tests;

import dto.User1;
import manager.AppManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.UserFactory;
import static utils.UserFactory.*;

import java.util.Random;

public class AuthorizationTests extends AppManager {
    LoginPage loginPage;


    @BeforeMethod
    public void goToLoginPage(){
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }
    @Test
    public void authorizationPositiveTest(){
       // int i = new Random().nextInt(1000); если меняются данные
        User1 user = User1.builder()
                .username("victor19802710@gmail.com")
                .password("4tzNnQrGn96S!!4")
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
                .password("4tzNnQrGn96S!!4")
                .build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isLoginFailedAlertPresent());
    }

    }



