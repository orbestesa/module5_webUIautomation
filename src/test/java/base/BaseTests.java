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
        driver.findElement(By.cssSelector("button[data-testid *= 'create-menu' ]")).click();
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
        System.out.println(driver.findElement(By.cssSelector("div[class*='mod-board-name'] h1")).getText());
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
