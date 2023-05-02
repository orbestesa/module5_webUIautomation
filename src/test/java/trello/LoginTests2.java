package trello;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trello.ui.pages.BoardsPage;
import trello.ui.pages.LoginAtlassianPage;
import trello.ui.pages.LoginTrelloPage;

import java.time.Duration;

public class LoginTests2 {
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
        //Navigate to Trello login page
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://trello.com/login");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown(){
        //Quit browser
        driver.quit();
    }

    @Test
    public void testLoginSuccessfully(){
        String userEmail = "dotinita1309@gmail.com";
        String userPassword = "Stefany13091986";
        LoginTrelloPage loginTrelloPage = new LoginTrelloPage(driver);
        loginTrelloPage.setEmail(userEmail);
        LoginAtlassianPage loginAtlassianPage = loginTrelloPage.clickContinueBtn();
        //loginAtlassianPage.setPassword(userPassword);
        //BoardsPage boardsPage = loginAtlassianPage.clickLoginBtn();
        //BoardsPage boardsPage = loginAtlassianPage.setPassword(userPassword).clickLoginBtn(); //Por que tiene la opcion de retornar un objecto de la misma clase puede adicionarse otro metodo
        BoardsPage boardsPage = loginAtlassianPage.loginToAtlassian(userPassword);
        boardsPage.getTopMenu().clickAccountMenuBtn();
        //Assert.assertTrue(boardsPage.getTopMenu().userEmailDisplay("dotinita1309@gmail.com"),"The user email was not displayed");
        Assert.assertEquals(boardsPage.getTopMenu().getUserEmail(),userEmail, "The user email is incorrect");
    }
}
