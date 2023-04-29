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

public class FileUploadTest {
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
    public void testUpldoadFile(){
        driver.get("http://the-internet.herokuapp.com/upload");
        By inputFielField = By.id("file-upload");
        By uploadButton = By.id("file-submit");
        By uploadedFiles = By.id("uploaded-files");

        //Set file path
        driver.findElement(inputFielField).sendKeys("C:\\Users\\HP\\Downloads\\osito.jpg");
        //Click upload button
        driver.findElement(uploadButton).click();
        //Verify the file name is displayed
        Assert.assertEquals("osito.jpg",driver.findElement(uploadedFiles).getText());
    }
}
