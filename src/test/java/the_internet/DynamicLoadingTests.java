package the_internet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;

public class DynamicLoadingTests {
    private WebDriver driver;
    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void dynamicLoadingExampleExplicitWait(){
        driver.get("http://the-internet.herokuapp.com/dynamic_loading");
        //Explicitly wait
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        //Click on example 1
        WebElement example1Link = driver.findElement(By.cssSelector("a[href*='1']"));
        example1Link.click();
        //Click on Start button
        WebElement startButton = driver.findElement(By.cssSelector("#start button"));
        startButton.click();
        //Validate message
        WebElement loadText = driver.findElement(By.cssSelector("#finish"));
        wait.until(ExpectedConditions.visibilityOf(loadText));
        System.out.println("Load Text:..." + loadText.getText());
    }

    @Test
    public void dynamicLoadingExampleFluentWait(){
        driver.get("http://the-internet.herokuapp.com/dynamic_loading");
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

    @Test
    public void fillFormTest() {
        driver.get("https://formy-project.herokuapp.com/form");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Fill in the First Name
        driver.findElement(By.id("first-name")).sendKeys("Carla");
        //Fill in the Last Name
        driver.findElement(By.id("last-name")).sendKeys("Rodriguez");
        //Fill in Job title
        driver.findElement(By.id("job-title")).sendKeys("Systems Engineer");
        //Select Highest Level of education
        driver.findElement(By.xpath("//div[@class='input-group']/div[contains(.,'College')]/input")).click();
        //Select sex
        driver.findElement(By.xpath("//div[@class='input-group']/div[contains(.,'Female')]/input")).click();
        //Select Years of experience
        Select experience = new Select(driver.findElement(By.id("select-menu")));
        experience.selectByVisibleText("0-1");
        //set a Date
        driver.findElement(By.xpath("//strong[contains(.,'Date')]/following::input")).sendKeys("04/28/2023");
        //Click on submit button
        driver.findElement(By.linkText("Submit")).click();
        //Verify the message
        Assert.assertEquals("The form was successfully submitted!",driver.findElement(By.className("alert-success")).getText());
        System.out.println(driver.findElement(By.className("alert-success")).getText());
    }


}
