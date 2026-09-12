package ui_tests;

import data_providers.UserDataProvider;
import dto.User1;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;
import utils.TestNGListener;

import static utils.UserFactory.*;

@Listeners(TestNGListener.class)
public class RegistrationTests extends AppManager {
    RegistrationPage registrationPage;


    @BeforeMethod(alwaysRun = true)
    public void goToRegistrationPage() {
        logger.info("Start registration test");
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test(groups = {"smoke","regress","user","positive"})
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
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain 1 uppercase letter, 1 lowercase letter," +
                "1 number and one special symbol of [@$#^&*!])"));

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
