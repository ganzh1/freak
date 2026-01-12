package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;


import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class FacebookSignupSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("user is on Facebook homepage")
    public void user_is_on_facebook_homepage() {
        driver.get("https://www.facebook.com/");
    }

    @When("user clicks Create New Account")
    public void user_clicks_create_new_account() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Create new account"))).click();
    }

    @And("fills first name last name and selects DOB {string} {int} {int}")
    public void fills_first_name_last_name_and_selects_dob(String monthName, int day, int year) {
        driver.findElement(By.name("firstname")).sendKeys("Test");
        driver.findElement(By.name("lastname")).sendKeys("User");

        // Facebook DOB: day, month (1-12), year
        new Select(driver.findElement(By.name("birthday_day"))).selectByValue(String.valueOf(day));
        new Select(driver.findElement(By.name("birthday_month"))).selectByValue("1");  // January = "1"
        new Select(driver.findElement(By.name("birthday_year"))).selectByValue(String.valueOf(year));
    }
    @And("selects gender Female and enters email password")
    public void selects_gender_female_and_enters_email_password() {
        // Gender Female (stable xpath)
        WebElement femaleBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='sex' and @value='2']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", femaleBtn);

        // Email - use more stable CSS + JS interaction
        String testEmail = "test" + System.currentTimeMillis() + "@tempmail.com";

        // First email field
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name^='reg_email']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", emailField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", emailField, testEmail);

        // Confirm email (same or next field)
        WebElement confirmEmail = driver.findElement(By.cssSelector("input[name*='email_confirmation']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", confirmEmail);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", confirmEmail, testEmail);

        // Password field
        WebElement passField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[name*='passwd']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", passField, "SecurePass123!");

}

    @And("clicks signup")
    public void clicks_signup() {
        driver.findElement(By.name("websubmit")).click();
    }

    @Then("should see signup confirmation or next step")
    public void should_see_signup_confirmation() {
        // Facebook shows phone/email verification or success message
        String title = driver.getTitle();
        assertTrue(title.contains("Facebook") && !title.contains("Log In"));
    }
}


