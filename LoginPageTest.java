import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Set the path to your WebDriver, e.g., ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize the WebDriver
        driver = new ChromeDriver();

        // Open the login page (assuming you have a local server running)
        driver.get("http://localhost:8080/login.html"); // Change URL as per your setup
    }

    @Test
    public void testLoginSuccess() throws InterruptedException {
        // Find the username field and enter the correct username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("user");

        // Find the password field and enter the correct password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("password");

        // Find the login button and click it
        WebElement loginButton = driver.findElement(By.cssSelector(".btn"));
        loginButton.click();

        // Wait for the page to redirect
        Thread.sleep(2000); // Add appropriate waiting mechanisms here like WebDriverWait

        // Check that we are on the welcome page
        assertEquals("http://localhost:8080/nextpage.html", driver.getCurrentUrl());

        // Verify the welcome message
        WebElement welcomeMessage = driver.findElement(By.tagName("h1"));
        assertEquals("Welcome!", welcomeMessage.getText());
    }

    @Test
    public void testLoginFailure() throws InterruptedException {
        // Find the username field and enter an incorrect username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("wrongUser");

        // Find the password field and enter an incorrect password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("wrongPassword");

        // Find the login button and click it
        WebElement loginButton = driver.findElement(By.cssSelector(".btn"));
        loginButton.click();

        // Wait for the error message to appear
        Thread.sleep(1000); // Add appropriate waiting mechanisms here like WebDriverWait

        // Check that the message is displayed and it is the correct error message
        WebElement message = driver.findElement(By.id("message"));
        assertEquals("Incorrect credentials, please try again.", message.getText());
        assertEquals("red", message.getCssValue("color"));
    }

    @AfterEach
    public void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}
