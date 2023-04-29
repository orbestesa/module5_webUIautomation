package todoly;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests {
    private WebDriver driver;
    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testLoginTodoLy(){
        driver.get("https://todo.ly/");
        driver.manage().window().maximize();
        //click login button
        driver.findElement(By.cssSelector("img[src*='pagelogin']")).click();
        //Set email
        driver.findElement(By.cssSelector("input[id*='TextBoxEmail'][id*='Login']")).sendKeys("yesica.7887@gmail.com");
        //Set password
        driver.findElement(By.cssSelector("input[id*='TextBoxPassword'][id*='Login']")).sendKeys("password");
        //Click login
        driver.findElement(By.cssSelector("input[id*='ButtonLogin']")).click();
        System.out.println("Title:..." + driver.getTitle());
    }
}
