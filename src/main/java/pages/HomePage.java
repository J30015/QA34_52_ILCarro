package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnYearOnCalendar;

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

    public void clickBtnSubmitWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\").removeAttribute('disabled')");
        clickWait(btnYalla);
    }

    public boolean isUrlContainsText(String text) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.urlContains(text));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;

    }

    public void typeSearchWithCalendar(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDates.click();
        typeCalendar(startDate);
        typeCalendar(endDate);
    }

    private void typeCalendar(LocalDate date) {
        btnYearOnCalendar.click();
        System.out.println(date.getYear());
        //td[@aria-label='2026']       "//td[@aria-label='"+ year+"']"
        String year = Integer.toString(date.getYear());
        WebElement btnYear =
                driver.findElement(By.xpath("//td[@aria-label='" + year + "']"));
        btnYear.click();
        //td[@aria-label="November 2026"]
        // "//td[@aria-label='"+month+" "+year+"']"
        System.out.println(date.getMonth());
        String month = createMonth(date.getMonth().toString());
        System.out.println(month);
        WebElement btnMonth = driver.findElement(By.xpath("//td[@aria-label='" + month + " " + year + "']"));
        btnMonth.click();
        System.out.println(date.getDayOfMonth());
        String day = String.valueOf(date.getDayOfMonth());
        WebElement btnDay = driver.findElement
                (By.xpath("//td[@aria-label='" + month + " " + day + ", " + year + "']"));
        btnDay.click();


    }

    //SEPTEMBER--> September
    private String createMonth(String month) {
        return new StringBuilder().append(month.substring(0, 1)
                .toUpperCase()).append(month.substring(1).toLowerCase()).toString();

    }

    public boolean isDateClickable(LocalDate date) {
        String day = String.valueOf(date.getDayOfMonth());
        WebElement dayElement = driver.findElement(By.xpath(
                "//td[@class='mat-calendar-body-cell mat-calendar-body-disabled ng-star-inserted']"
        ));
        String classes = dayElement.getAttribute("class");
        if (classes.contains("mat-calendar-body-disabled")) {
            return false;
        }

        return true;
    }


}

