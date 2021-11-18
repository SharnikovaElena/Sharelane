import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SingUpTest {

    @Test
    public void zipCodeShouldAccept5Digits() {
        // Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        // Ввести 5 цифр
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        // Нажимаем кнопку Continue
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        // Убедиться, что мы на странице SignUp
        boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        // Закрыть браузер
        driver.quit();
    }

    @Test
    public void zipCodeShouldAccept4Digits() {
        // Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        // Ввести 4 цифры
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("1234");
        // Нажимаем кнопку Continue
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        // Убедиться, что на странице появилось уведомление "Oops, error on page. ZIP code should have 5 digits"
        Boolean isDisplayed = driver.findElement(By.className("error_message")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        String expectedMessage = "Oops, error on page. ZIP code should have 5 digits";
        String actualMessage = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        Assert.assertEquals(actualMessage, expectedMessage);
        // Закрыть браузер
        driver.quit();
    }

    @Test
    public void zipCodeShouldAccept6Digits() {
        // Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        // Ввести 6 цифры
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("123456");
        // Нажимаем кнопку Continue
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        // Убедиться, что на странице появилось уведомление "Oops, error on page. ZIP code should have 5 digits"
        Boolean isDisplayed = driver.findElement(By.className("error_message")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        String expectedMessage = "Oops, error on page. ZIP code should have 5 digits";
        String actualMessage = driver.findElement(By.className("error_message")).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Поле Zip code не должно принимать больше 5 цифр");
        // Закрыть браузер
        driver.quit();
    }


    //Домашнее задание - тестирование формы Sign Up

    WebDriver driver;

    @BeforeTest
    public void launchBrowser() {
        // Открытие страницы "https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345"
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void singUpRegisterValid() {
        // Заполняем поле First Name
        WebElement firstNameField = driver.findElement(By.name("first_name"));
        firstNameField.sendKeys("Elena");
        // Заполняем поле Last Name
        WebElement lastNameField = driver.findElement(By.name("last_name"));
        lastNameField.sendKeys("Sharnikova");
        // Заполняем поле Email
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("check@gmail.com");
        // Заполняем поле Password
        WebElement passwordField = driver.findElement(By.name("password1"));
        passwordField.sendKeys("TMS_09");
        // Заполняем поле Confirm Password
        WebElement confirmPasswordField = driver.findElement(By.name("password2"));
        confirmPasswordField.sendKeys("TMS_09");
        // Нажимаем кнопку Register
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        // Убедиться, что на странице появилось уведомление  "Account is created!"
        Assert.assertEquals(driver.findElement(By.className("confirmation_message")).getText(), "Account is created!", "This is a bug. Account not created");
    }

    @Test
    public void allFieldsEmpty() {
        WebElement firstNameField = driver.findElement(By.name("first_name"));
        firstNameField.sendKeys(" ");
        WebElement lastNameField = driver.findElement(By.name("last_name"));
        lastNameField.sendKeys(" ");
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys(" ");
        WebElement passwordField = driver.findElement(By.name("password1"));
        passwordField.sendKeys(" ");
        WebElement confirmPasswordField = driver.findElement(By.name("password2"));
        confirmPasswordField.sendKeys(" ");
        // Нажимаем кнопку Register
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        // Убедиться, что на странице появилось уведомление "Oops, error on page. Some of your fields have invalid data or email was previously used"
        Assert.assertEquals(driver.findElement(By.className("error_message")).getText(), "Oops, error on page. Some of your fields have invalid data or email was previously used", "This is a bug.Account cannot be created with all empty fields");
    }

    @Test
    public void firstNameFieldInValid() {
        // Вводим невалидное знанчение в поле First_name - "123@!"
        WebElement firstNameField = driver.findElement(By.name("first_name"));
        firstNameField.sendKeys("123@!");
        WebElement lastNameField = driver.findElement(By.name("last_name"));
        lastNameField.sendKeys("Sharnikova");
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("check@gmail.com");
        WebElement passwordField = driver.findElement(By.name("password1"));
        passwordField.sendKeys("TMS_09");
        WebElement confirmPasswordField = driver.findElement(By.name("password2"));
        confirmPasswordField.sendKeys("TMS_09");
        // Нажимаем кнопку Register
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        // Убедиться, что на странице появилось уведомление "Oops, error on page. Some of your fields have invalid data or email was previously used"
        Assert.assertEquals(driver.findElement(By.className("error_message")).getText(), "Oops, error on page. Some of your fields have invalid data or email was previously used", "Account cannot be created. Incorrect value in the First_Name field");
    }

    @Test
    public void passwordConfirmationInvalid() {
        WebElement firstNameField = driver.findElement(By.name("first_name"));
        firstNameField.sendKeys("Elena");
        WebElement lastNameField = driver.findElement(By.name("last_name"));
        lastNameField.sendKeys(" ");
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("check@gmail.com");
        // Заполняем поле Password
        WebElement passwordField = driver.findElement(By.name("password1"));
        passwordField.sendKeys("TMS_09");
        // Заполняем поле Confirm Password невалидным паролем TMS_01
        WebElement confirmPasswordField = driver.findElement(By.name("password2"));
        confirmPasswordField.sendKeys("TMS_01");
        // Нажимаем кнопку Register
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        // Убедиться, что на странице появилось уведомление "Oops, error on page. Some of your fields have invalid data or email was previously used"
        Assert.assertEquals(driver.findElement(By.className("error_message")).getText(), "Oops, error on page. Some of your fields have invalid data or email was previously used", "Account cannot be created. The password in the Password and Confirm Password fields must match");
    }

    @Test
    public void hidePassword1Valid() {
        WebElement passwordField = driver.findElement(By.name("password1"));
        Assert.assertEquals(passwordField.getAttribute("type"), "password", "This is a bug. The password is not hidden");
    }

    @Test
    public void hidePassword2Valid() {
        WebElement passwordField = driver.findElement(By.name("password2"));
        Assert.assertEquals(passwordField.getAttribute("type"), "password", "This is a bug. The password is not hidden");
    }

    @Test
    public void emailInvalid() {
        WebElement firstNameField = driver.findElement(By.name("first_name"));
        firstNameField.sendKeys("Elena");
        WebElement lastNameField = driver.findElement(By.name("last_name"));
        lastNameField.sendKeys(" ");
        // Вводим невалидный email "check"
        WebElement emailField = driver.findElement(By.className("email"));
        emailField.sendKeys("check");
        WebElement passwordField = driver.findElement(By.name("password1"));
        passwordField.sendKeys("TMS_09");
        WebElement confirmPasswordField = driver.findElement(By.name("password2"));
        confirmPasswordField.sendKeys("TMS_09");
        // Нажимаем кнопку Register
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        // Убедиться, что на странице появилось уведомление "Oops, error on page. Some of your fields have invalid data or email was previously used"
        Assert.assertEquals(driver.findElement(By.className("error_message")).getText(), "Oops, error on page. Some of your fields have invalid data or email was previously used", "Account cannot be created. Email is incorrect");
    }
}