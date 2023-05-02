package trello;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class BoardsTests {
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
    public void testCreateNewBoardTrello(){
        driver.get("https://trello.com/home");
        driver.manage().window().maximize();
        //login in Trello
        driver.findElement(By.cssSelector("a[href*='login'][data-uuid*='login']")).click();
        driver.findElement(By.cssSelector("input[id='user']")).sendKeys("dotinita1309@gmail.com");
        driver.findElement(By.cssSelector("input[id='login']")).click();
        driver.findElement(By.cssSelector("input[id='password'][aria-labelledby*='password']")).sendKeys("Stefany13091986");
        driver.findElement(By.cssSelector("button[id='login-submit']")).click();
        //Click on plus button
        driver.findElement(By.cssSelector("button[data-testid *= 'create-menu']")).click();
        //Click on Create Board option
        driver.findElement(By.cssSelector("section[data-testid*='create-menu'] button[data-testid*= 'create-board-button' ]")).click();
        //Set Board Name
        WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[data-testid*='board-title']")));
        firstResult.sendKeys("Nuevito");
        //Display menu type
        driver.findElement(By.xpath("//div[contains(@id,'board-select-visibility')]")).click();
        //Select public type
        driver.findElement(By.xpath("//div[@id='react-select-3-option-2']/li/div[2]/div[2]/div/span")).click();
        //Click on Make board public
        driver.findElement(By.xpath("//div[@class='atlaskit-portal']/section/div/div/button")).click();
        //Click on create board
        driver.findElement(By.cssSelector("button[data-testid*='create-board']")).click();
        //Validate the board name
        firstResult = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class*='mod-board-name'] h1")));
        System.out.println(driver.findElement(By.cssSelector("div[data-testid='board-name-container'] h1")).getText());
    }

}
