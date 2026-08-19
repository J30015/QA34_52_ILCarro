package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static utils.PropertiesReader.*;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
        //driver.get("https://ilcarro.web.app/search");
        driver.get(getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);

    }

    @FindBy(xpath = "//div[@class='header']/a[6]")
    WebElement btnLogin;


    public void clickBtnLogin() {
        btnLogin.click();
    }

    @FindBy(xpath = "//a[@href='/logout?url=%2Fsearch']")
    WebElement btnLogOut;

    public void clickBtnLogOut() {
        btnLogOut.click();
    }
    @FindBy(xpath = "//a[@href='/registration?url=%2Fsearch']")
    WebElement btnSignUp;
    public void clickBtnSignUp(){
        btnSignUp.click();
    }

}

