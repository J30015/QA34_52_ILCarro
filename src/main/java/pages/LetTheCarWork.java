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
@FindBy(id ="photos")
WebElement inputImage;


    public void clickBtnSubmitWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\").removeAttribute('disabled')");
        clickWait(btnSubmit);
    }
    private void chooseFuel(Fuel fuel){
        inputFuel.click();
        driver.findElement(By.xpath(fuel.getLocator())).click();

    }
    public void downLoadImage(String fileName){
        inputImage.sendKeys(new File("src/test/resources/"+ fileName).getAbsolutePath());

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
    public void typeAddNewCarForm(Car car){
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
}