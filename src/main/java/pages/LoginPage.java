package pages;

import dto.User1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements
                (new AjaxElementLocatorFactory(driver, 10), this);
    }
    @FindBy(xpath = "//label[@for='email']")
    WebElement fieldEmail;

    @FindBy(xpath = "//div[@class='input-container']/input[@id='email']")
    WebElement inputEmail;
    @FindBy(xpath = "//label[@for='password']")
    WebElement fieldPassword;

    @FindBy(xpath = "//div[@class='input-container']/input[@id='password']")
    WebElement inputPassword;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    public void typeLoginForm(User1 user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());


    }

    public void clickBtnYalla() {
        btnYalla.click();
    }
    public boolean isEmailAndPasswordFilled() {
        String email = fieldEmail.getText();
        String password = fieldPassword.getText();

        return email != null && !email.isEmpty()
                && password != null && !password.isEmpty();
    }

    public boolean isLoginFailedAlertPresent() {
        WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='dialog-container']")));

        String text = alert.getText();
        alert.findElement(By.xpath("//button[@type='button']")).click();
        return text.contains("Login failed");
    }
    public void closeHtmlAlert() {
        WebElement okButton = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']")));
        okButton.click();
    }
}

