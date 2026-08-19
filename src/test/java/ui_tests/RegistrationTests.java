package ui_tests;

import data_providers.UserDataProvider;
import dto.User1;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

import static utils.PropertiesReader.getProperty;

public class RegistrationTests extends AppManager {
    RegistrationPage registrationPage;

    @BeforeMethod
    public void goToRegistrationPage() {
        
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {

        User1 user = User1.builder()
                .firstName("Jamily")
                .lastName("Kasimova")
                .username("jkasimova80@gmail.com")
                .password("Ff2$Ss2$")
                .build();


        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBox();
        registrationPage.clickBtnYalla();
    }
    @Test(dataProvider = "dataProviderWrongPasswordOrEmail",dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongPasswordTest(User1 user){
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBox();
        registrationPage.clickBtnYalla();

    }

}
