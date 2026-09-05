package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;

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
    @FindBy(id = "city")
    WebElement inputCity;
    @FindBy(id = "dates")
    WebElement inputDates;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//div[@class='error']")
    WebElement messageErrorCity;
    @FindBy(xpath = "//div[@class='error']/div[1]")
    WebElement messageInvalidDateFirst;
    @FindBy(xpath = "//div[@class='error']/div[2]")
    WebElement messageInvalidDateSecond;
    @FindBy(xpath = "//div[@class='error']/div[3]")
    WebElement messageInvalidDateThird;
    @FindBy(xpath = "//div[@class='ng-star-inserted']")
    WebElement messageDateRequired;
    @FindBy(xpath = "//button[@class='dismissButton']")
    WebElement popUpBtnOk;

    public void clickPopUpBtnOk() {
        popUpBtnOk.click();
    }

    public void typeCityOnly(String city) {
        inputCity.sendKeys(city);
    }

    public void onlyClickFieldDate() {
        inputDates.click();
    }

    public void typeSearchForm(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        System.out.println(startDate);
        System.out.println(endDate);
        // 2026-09-04
        // 9/4/2026 - 9/10/2026
        System.out.println(startDate.getMonthValue());
        System.out.println(startDate.getDayOfMonth());
        String dates = startDate.getMonthValue() + "/" + startDate.getDayOfMonth() + "/" + startDate.getYear() + " - "
                + endDate.getMonthValue() + "/" + endDate.getDayOfMonth() + "/" + endDate.getYear();
        System.out.println(dates);
        inputDates.sendKeys(dates);
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }

    public void clickBtnSignUp() {
        btnSignUp.click();
    }

    public boolean isYallaButtonEnabled() {
        return btnYalla.isEnabled();
    }

    public boolean isMessageErrorCityDisplayed() {
        return isElementDisplayed(messageErrorCity);
    }

    public boolean isMessageFirstInvalidDateDisplayed() {
        return isElementDisplayed(messageInvalidDateFirst);
    }

    public boolean isMessageSecondInvalidDateDisplayed() {
        return isElementDisplayed(messageInvalidDateSecond);
    }

    public boolean isMessageThirdInvalidDateDisplayed() {
        return isElementDisplayed(messageInvalidDateThird);
    }

    public boolean isMessageDateRequiredDisplayed() {
        return isElementDisplayed(messageDateRequired);
    }

    public boolean isErrorMessagePresent() {
        return !driver.findElements(By.xpath("//div[@class='ng-star-inserted']")).isEmpty();
    }


}

