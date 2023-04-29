package the_internet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AlertTests {
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
    public void testAcceptAlert(){
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
        driver.manage().window().maximize();
        //Click JSAlert button
        driver.findElement(By.cssSelector("button[onclick*='jsAlert']")).click();
        //Accept alert
        driver.switchTo().alert().accept();
        //Verify successful message
        Assert.assertEquals("You successfully clicked an alert",
                driver.findElement(By.id("result")).getText(),"Result text is incorrect");
    }

    @Test
    public void testgetTextFromAlert(){
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
        driver.manage().window().maximize();
        //Click JS Confirm button
        driver.findElement(By.cssSelector("button[onclick*='jsConfirm']")).click();
        //Get text alert
        String text = driver.switchTo().alert().getText();
        //Cancel the alert
        driver.switchTo().alert().dismiss();
        //Verify successful message
        Assert.assertEquals("I am a JS Confirm",
                text,"Text alert is incorrect");
    }

    @Test
    public void testSetInputInAlert(){
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
        driver.manage().window().maximize();
        //Click JSAlert button
        driver.findElement(By.cssSelector("button[onclick*='jsPrompt']")).click();
        //Input a message in alert
        String text = "Hello world";
        driver.switchTo().alert().sendKeys(text);
        //Accept alert
        driver.switchTo().alert().accept();
        //Verify successful message
        Assert.assertEquals("You entered: "+text,
                driver.findElement(By.id("result")).getText(),"Result text is incorrect");
    }
}
