package ui_tests;

import data_providers.UserDataProvider;
import dto.User1;
import manager.AppManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PopUpPage;
import pages.RegistrationPage;

import static utils.PropertiesReader.getProperty;
import static utils.UserFactory.*;

public class RegistrationTests extends AppManager {
    RegistrationPage registrationPage;


    @BeforeMethod
    public void goToRegistrationPage() {
        logger.info("Start registration test");
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        User1 user = positiveUser();

//        User1 user = User1.builder()
//                .firstName("Jamily")
//                .lastName("Kasimova")
//                .username("jkasimova80@gmail.com")
//                .password("Ff2$Ss2$")
//                .build();


        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckboxTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test(dataProvider = "dataProviderForRegistrationWrongPasswordOrEmail", dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongPasswordTest(User1 user) {
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBox();
        registrationPage.clickBtnYalla();

    }

    @Test
    public void registrationPositiveWithActionsTest() {
        User1 user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }

}
