package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonUtils;

public class AmazonLoginPage {
    private WebDriver driver;

    @FindBy(id = "ap_email")
    private WebElement emailField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "ap_password")
    private WebElement passwordField;

    @FindBy(id = "signInSubmit")
    private WebElement signInButton;

    public AmazonLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        CommonUtils.waitForElementVisible(driver, emailField, 10);
        emailField.clear();
        emailField.sendKeys(email);
        continueButton.click();
    }

    public void enterPassword(String password) {
        CommonUtils.waitForElementVisible(driver, passwordField, 10);
        passwordField.clear();
        passwordField.sendKeys(password);
        signInButton.click();
    }

    public void loginAmazon(String email, String password) {
        enterEmail(email);
        enterPassword(password);
    }
}

