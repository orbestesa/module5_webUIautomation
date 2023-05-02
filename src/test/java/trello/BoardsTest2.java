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
import trello.ui.pages.*;

import java.time.Duration;

public class BoardsTest2 {
    private WebDriver driver;
    WebDriverWait wait;
    BoardsPage boardsPage;
    LoginTrelloPage loginTrelloPage;
    LoginAtlassianPage loginAtlassianPage;
    CreateMenuPage createMenuPage;
    CreateBoardPage createBoardPage;
    BoardPage boardPage;

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //Navigate to Trello login page
        driver.get("https://trello.com/login");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown(){
        //Quit browser
        driver.quit();
    }

    @Test
    public void testCreateBoardFromTopMenu()
    {
        String boardName = "My board";
        //Login Trello
        String userEmail = "dotinita1309@gmail.com";
        String userPassword = "Stefany13091986";
        loginTrelloPage = new LoginTrelloPage(driver);
        loginAtlassianPage = loginTrelloPage.loginTrello(userEmail);
        boardsPage = loginAtlassianPage.loginToAtlassian(userPassword);
        //Open menu create
        createMenuPage = boardsPage.getTopMenu().openCreateMenu();
        //Open create board
        createBoardPage = createMenuPage.clickCreateBoardsBtn();
        //Create board
        boardPage = createBoardPage.createBoard(boardName);
        //Verify board is displayed
        Assert.assertEquals(boardPage.getBoardTitle(),boardName, "The board name is incorrect");
    }

    @Test
    public void testCreateBoardFromBoardPanel()
    {
        String boardName = "My board1";
        String wsName = "AT07-TestStef";
        //Login Trello
        String userEmail = "dotinita1309@gmail.com";
        String userPassword = "Stefany13091986";
        loginTrelloPage = new LoginTrelloPage(driver);
        loginAtlassianPage = loginTrelloPage.loginTrello(userEmail);
        boardsPage = loginAtlassianPage.loginToAtlassian(userPassword);
        //Open create board
        createBoardPage = boardsPage.getMiddleContent().clickCreateBoardBtnFromWS(wsName);
        //Create board
        boardPage = createBoardPage.createBoard(boardName);
        //Verify board is displayed
        Assert.assertEquals(boardPage.getBoardTitle(),boardName, "The board name is incorrect");
    }
}
