package trello;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class LoginTests {
    private WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testLoginTrello(){
        driver.get("https://trello.com/home");
        driver.manage().window().maximize();
        //print main message
        System.out.println(driver.findElement(By.cssSelector("main[id='skip-target']>section>div>div>div>div>div>h1")).getText());
        System.out.println(driver.findElement(By.xpath("//main[@id='skip-target']/section/div/div/div/div/div/h1/following-sibling::p")).getText());
        //click login button
        driver.findElement(By.cssSelector("a[href*='login'][data-uuid*='login']")).click();
        //Set email
        driver.findElement(By.cssSelector("input[id='user']")).sendKeys("dotinita1309@gmail.com");
        //Click continue
        driver.findElement(By.cssSelector("input[id='login']")).click();
        //Set password
        //wait(5000);
        driver.findElement(By.cssSelector("input[id='password'][aria-labelledby*='password']")).sendKeys("Stefany13091986");
        //Click login
        driver.findElement(By.cssSelector("button[id='login-submit']")).click();
        //Catch menu left elements
        List<WebElement> listElements = driver.findElements(By.cssSelector("nav[class*='left']>div>ul>*"));
        for (WebElement element: listElements) {
            System.out.println("Text element: "+ element.getText());
        }
        //Logout
    }
}
