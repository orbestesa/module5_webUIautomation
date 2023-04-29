package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class BaseTests {
    private WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setUp(){
        /*System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        /*WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();*/

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //options.addArguments("--headless");
        //options.setHeadless(true);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }


    @Test
    public void testDynamicLoading(){
        driver.get("http://the-internet.herokuapp.com/dynamic_loading");
        //Explicitly wait
        //WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        //Fluent wait
        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        //Click on example 1
        WebElement example1Link = driver.findElement(By.cssSelector("a[href*='1']"));
        example1Link.click();
        //Click on Start button
        WebElement startButton = driver.findElement(By.cssSelector("#start button"));
        startButton.click();
        //Validate message
        //WebElement loadText = driver.findElement(By.cssSelector("#finish")); // aqui no ignora el such exception
        //wait.until(ExpectedConditions.visibilityOf(loadText));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#finish"))); //aqui deberi ignorar el such exception
        WebElement loadText = driver.findElement(By.cssSelector("#finish"));
        System.out.println("Load Text:..." + loadText.getText());
    }
    public static void main(String[] args) {

    }
}
