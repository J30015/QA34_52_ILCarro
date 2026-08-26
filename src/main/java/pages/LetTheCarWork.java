package pages;

import dto.Car;
import dto.User1;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LetTheCarWork extends BasePage {
    public LetTheCarWork(WebDriver driver) {
        PageFactory.initElements
                (new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//input[@id='pickUpPlace']")
    WebElement location;
    @FindBy(xpath = "//button[@class='dismissButton']")
    WebElement btnPopOk;

    @FindBy(xpath = "//input[@id='make']")
    WebElement manufacture;
    @FindBy(xpath = "//input[@id='model']")
    WebElement model;
    @FindBy(xpath = "//input[@id='year']")
    WebElement year;
    @FindBy(xpath = "//select[@id='fuel']")
    WebElement fuel;
    @FindBy(xpath = "//input[@id='seats']")
    WebElement seats;
    @FindBy(xpath = "//input[@id='class']")
    WebElement car_class;
    @FindBy(xpath = "//input[@id='serialNumber']")
    WebElement number;
    @FindBy(xpath = "//input[@id='price']")
    WebElement price;
    @FindBy(xpath = "//textarea[@id='about']")
    WebElement textAbout;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;
    @FindBy(xpath = "//div[@class='dialog-container']")
    WebElement popUpCarAddingFailed;


    public void clickBtnSubmitWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\").removeAttribute('disabled')");
        clickWait(btnSubmit);
    }

    public void clickBtnPopOk() {
        btnPopOk.click();
    }


    public void typeAddress(User1 user) {
        location.click();
        location.sendKeys(user.getLocation());

    }

    public void typeDetailsCar(Car car) {
        manufacture.sendKeys(car.getManufacture());
        model.sendKeys(car.getModel());
        year.sendKeys(car.getYear());
        typesOfFuel(car.getFuel_types());
        seats.sendKeys(car.getSeats());
        car_class.sendKeys(car.getCarClass());
        number.sendKeys(car.getNumber());
        price.sendKeys(car.getPrice());
        textAbout.sendKeys(car.getTextAbout());

    }


    private void typesOfFuel(String[] types) {
        fuel.sendKeys(types);
        fuel.sendKeys(Keys.ENTER);

    }

    public boolean isPopUpCarAddingFailedDisplayed() {
        return isElementDisplayed(popUpCarAddingFailed);
    }
}