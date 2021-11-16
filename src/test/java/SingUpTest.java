import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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
        Boolean isDisplayed = driver.findElement(By.cssSelector("[class=error_message]")).isDisplayed();
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
        Boolean isDisplayed = driver.findElement(By.cssSelector("[class=error_message]")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        String expectedMessage = "Oops, error on page. ZIP code should have 5 digits";
        String actualMessage = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        Assert.assertEquals(actualMessage, expectedMessage);

        // Закрыть браузер
        driver.quit();
    }
}
