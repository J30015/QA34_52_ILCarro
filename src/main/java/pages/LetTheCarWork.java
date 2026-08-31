package pages;

import dto.Car;
import dto.User1;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.enums.Fuel;

import java.io.File;

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
    WebElement inputFuel;
    @FindBy(xpath = "//input[@id='seats']")
    WebElement seats;
    @FindBy(xpath = "//input[@id='class']")
    WebElement car_class;
    @FindBy(xpath = "//input[@id='serialNumber']")
    WebElement inputSerialNumber;
    @FindBy(xpath = "//input[@id='price']")
    WebElement price;
    @FindBy(xpath = "//textarea[@id='about']")
    WebElement textAbout;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;
    @FindBy(xpath = "//div[@class='dialog-container']")
    WebElement popUpCarAddingFailed;
    @FindBy(id = "photos")
    WebElement inputImage;
    @FindBy(xpath = "//h2[@class='message']")
    WebElement popUpTextMessage;

    @FindBy(xpath = "//div[text()=' Wrong address ']")
    WebElement wrongAddressMessage;
    @FindBy(xpath = "//div[text()=' Make is required ']")
    WebElement makeRequiredMessage;
    @FindBy(xpath = "//div[text()=' Model is required ']")
    WebElement modelRequiredMessage;
    @FindBy(xpath = "//div[text()=' Year required ']")
    WebElement yearRequiredMessage;
    @FindBy(xpath = "//div[text()=' Fuel is required ']")
    WebElement fuelRequiredMessage;
    @FindBy(xpath = "//div[text()=' Number of seats is required ']")
    WebElement seatsRequiredMessage;
    @FindBy(xpath = "//div[text()=' Car class is required ']")
    WebElement carClassRequiredMessage;
    @FindBy(xpath = "//div[text()='Car registration number is required']")
    WebElement numberRequiredMessage;
    @FindBy(xpath = "//div[text()=' Price is required ']")
    WebElement priceRequiredMessage;
    @FindBy(xpath = "//textarea[@placeholder='About (max 500 chars)']")
    WebElement aboutMessage;
    @FindBy(xpath = "//h2[@class='message']")
    WebElement messageModelAbs;


    public void clickBtnSubmitWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\").removeAttribute('disabled')");
        clickWait(btnSubmit);
    }

    public void clickBtnSubmit() {
        btnSubmit.click();
    }

    private void chooseFuel(Fuel fuel) {
        inputFuel.click();
        driver.findElement(By.xpath(fuel.getLocator())).click();

    }

    public void downLoadImage(String fileName) {
        inputImage.sendKeys(new File("src/test/resources/" + fileName).getAbsolutePath());

    }

    public void clickBtnPopOk() {
        btnPopOk.click();
    }


    public void typeAddress(User1 user) {
        location.click();
        location.sendKeys(user.getLocation());

    }

//    public void typeDetailsCar(Car car) {
//        manufacture.sendKeys(car.getManufacture());
//        model.sendKeys(car.getModel());
//        year.sendKeys(car.getYear());
//        typesOfFuel(car.getFuel_types());
//        seats.sendKeys(car.getSeats());
//        car_class.sendKeys(car.getCarClass());
//        number.sendKeys(car.getNumber());
//        price.sendKeys(car.getPrice());
//        textAbout.sendKeys(car.getTextAbout());
//
//    }


//    private void typesOfFuel(String[] types) {
//        fuel.sendKeys(types);
//        fuel.sendKeys(Keys.ENTER);
//
//    }

    public boolean isPopUpCarAddingFailedDisplayed() {
        return isElementDisplayed(popUpCarAddingFailed);
    }

    public boolean isPopUpTextMessageDisplayed() {
        return isElementDisplayed(popUpTextMessage);
    }

    public boolean isWrongAddressMessageDisplayed() {
        return isElementDisplayed(wrongAddressMessage);
    }

    public boolean isMakeRequiredMessageDisplayed() {
        return isElementDisplayed(makeRequiredMessage);
    }

    public boolean isModelRequiredMessageDisplayed() {
        return isElementDisplayed(modelRequiredMessage);
    }

    public boolean isYearRequiredMessageDisplayed() {
        return isElementDisplayed(yearRequiredMessage);
    }

    public boolean isFuelRequiredMessageDisplayed() {
        return isElementDisplayed(fuelRequiredMessage);
    }

    public boolean isSeatsRequiredMessageDisplayed() {
        return isElementDisplayed(seatsRequiredMessage);
    }

    public boolean isCarClassRequiredMessageDisplayed() {
        return isElementDisplayed(carClassRequiredMessage);
    }

    public boolean isNumberRequiredMessageDisplayed() {
        return isElementDisplayed(numberRequiredMessage);
    }

    public boolean isPriceRequiredMessageDisplayed() {
        return isElementDisplayed(priceRequiredMessage);
    }

    public boolean isAboutMessageDisplayed() {
        return isElementDisplayed(aboutMessage);
    }

    public void typeAddNewCarForm(Car car) {
        location.sendKeys(car.getCity());
        manufacture.sendKeys(car.getManufacture());
        model.sendKeys(car.getModel());
        year.sendKeys(car.getYear());
        chooseFuel(car.getFuel());
        // seats.sendKeys(car.getSeats().toString()); любой вариант правильный
        //seats.sendKeys(String.valueOf(car.getSeats()));
        // seats.sendKeys(car.getSeats()+"");
        seats.sendKeys(Integer.toString(car.getSeats()));
        car_class.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
        price.sendKeys(Double.toString(car.getPricePerDay()));
        textAbout.sendKeys(car.getAbout());

    }

    public void typeAddNewCarFormWithoutModelType(Car car) {
        location.sendKeys(car.getCity());
        manufacture.sendKeys(car.getManufacture());
        model.click();
        year.sendKeys(car.getYear());
        chooseFuel(car.getFuel());
        seats.sendKeys(Integer.toString(car.getSeats()));
        car_class.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
        price.sendKeys(Double.toString(car.getPricePerDay()));
        textAbout.sendKeys(car.getAbout());

    }

    public void clickAddNewCarForm() {
        location.click();
        manufacture.click();
        model.click();
        year.click();
        inputFuel.click();
        seats.click();
        car_class.click();
        inputSerialNumber.click();
        price.click();
        textAbout.click();
    }
}