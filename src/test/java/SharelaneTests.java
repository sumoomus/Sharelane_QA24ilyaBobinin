import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SharelaneTests {
    private static final String URL = "https://sharelane.com/cgi-bin/main.py";

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void navigate() {
        driver.get(URL);

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void positiveRegistrationTest() throws InterruptedException {

        WebElement signUpButton = driver.findElement(By.xpath("//a[@href='./register.py']"));
        signUpButton.click();

        WebElement zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("12345");

        WebElement continueButton = driver.findElement(By.cssSelector("input[value=Continue]"));
        continueButton.click();

        zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        Assert.assertFalse(zipCodeInput.isDisplayed());

        WebElement firstNameInput = driver.findElement(By.cssSelector("input[name=first_name]"));
        Assert.assertTrue(firstNameInput.isDisplayed());
        firstNameInput.clear();
        firstNameInput.sendKeys("dfgfd");

        WebElement lastNameInput = driver.findElement(By.cssSelector("input[name=last_name]"));
        Assert.assertTrue(lastNameInput.isDisplayed());
        lastNameInput.clear();
        lastNameInput.sendKeys("dfgfdg");


        WebElement emailInput = driver.findElement(By.cssSelector("input[name=email]"));
        Assert.assertTrue(emailInput.isDisplayed());
        emailInput.clear();
        emailInput.sendKeys("terst566778@mailinator.com");

        WebElement passwordInput = driver.findElement(By.cssSelector("input[name=password1]"));
        Assert.assertTrue(passwordInput.isDisplayed());
        passwordInput.clear();
        passwordInput.sendKeys("123qwe");

        WebElement confirmPasswordInput = driver.findElement(By.cssSelector("input[name=password2]"));
        Assert.assertTrue(confirmPasswordInput.isDisplayed());
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys("123qwe");

        WebElement registration = driver.findElement(By.cssSelector("input[value= Register]"));
        registration.click();

        WebElement successMessage = driver.findElement(By.cssSelector(".confirmation_message"));
        Assert.assertTrue(successMessage.isDisplayed());
        String expectedSuccessMessage = "Account is created!";
        Assert.assertEquals(successMessage.getText(), expectedSuccessMessage);

        WebElement hereButton = driver.findElement(By.xpath("//a[@href='./main.py']"));
        hereButton.click();
    }

    @Test
    public void negativeRegistrationTest() throws InterruptedException {

        WebElement signUpButton = driver.findElement(By.xpath("//a[@href='./register.py']"));
        signUpButton.click();

        WebElement zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("1234");

        WebElement continueButton = driver.findElement(By.cssSelector("input[value=Continue]"));
        continueButton.click();

        zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        Assert.assertTrue(zipCodeInput.isDisplayed());

        WebElement errorMessage = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertTrue(errorMessage.isDisplayed());
        String expectedErrorMessage = "Oops, error on page. ZIP code should have 5 digits";
        Assert.assertEquals(errorMessage.getText(), expectedErrorMessage);


    }

    @Test
    public void negativeRegistrationTestSighUp() throws InterruptedException {

        WebElement signUpButton = driver.findElement(By.xpath("//a[@href='./register.py']"));
        signUpButton.click();

        WebElement zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("12345");

        WebElement continueButton = driver.findElement(By.cssSelector("input[value=Continue]"));
        continueButton.click();

        WebElement firstNameInput = driver.findElement(By.cssSelector("input[name=first_name]"));
        Assert.assertTrue(firstNameInput.isDisplayed());
        firstNameInput.clear();
        firstNameInput.sendKeys(" ");

        WebElement registration = driver.findElement(By.cssSelector("input[value= Register]"));
        registration.click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertTrue(errorMessage.isDisplayed());
        String expectedErrorMessage = "Oops, error on page. Some of your fields have invalid data or email was previously used";
        Assert.assertEquals(errorMessage.getText(), expectedErrorMessage);


    }
}
