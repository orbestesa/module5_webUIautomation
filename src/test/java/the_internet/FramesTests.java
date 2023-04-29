package the_internet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class FramesTests {
    private WebDriver driver;
    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void framesTest(){
        String text1 = "Hello";
        String text2 = "World";

        driver.get("http://the-internet.herokuapp.com/tinymce");
        String editorIFrameId = "mce_0_ifr";
        By textAreaBy = By.id("tinymce");
        By increaseIndentButton = By.cssSelector("button[title='Increase indent']");

        //Clear text area
        driver.switchTo().frame(editorIFrameId);
        WebElement textArea = driver.findElement(textAreaBy);
        textArea.clear();
        //Set up text in the text area
        textArea.sendKeys(text1);
        //Click on increase indent button
        driver.switchTo().parentFrame();
        driver.findElement(increaseIndentButton).click();
        //Set another text in the text area
        driver.switchTo().frame(editorIFrameId);
        textArea.sendKeys(text2);
        //Verify the text
        String actualText = textArea.getText();
        driver.switchTo().parentFrame();

        Assert.assertEquals(actualText,text1+text2,"Text from frame edito is incorrect ");
    }
}
