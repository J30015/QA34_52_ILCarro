package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;

public class AuthorizationTests extends AppManager {

    @BeforeMethod
    public void goToLoginPage(){
        new HomePage(getDriver()).clickBtnLogin();
    }
    @Test
    public void authorizationPositiveTest(){
        int i = new Random().nextInt(1000);
        User user = User.builder()
                .username("victor19802710@gmail.com")
                .password("4tzNnQrGn96S!!4")
                .build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();


    }
}

