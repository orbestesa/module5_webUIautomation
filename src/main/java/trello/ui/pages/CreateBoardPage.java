package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateBoardPage {
    WebDriver driver;
    WebDriverWait wait;
    private By boardTitleTxtBoxBy = By.cssSelector("[data-testid*='board-title']");;
    private By visibilityCmbBoxBy = By.xpath("//div[contains(@id,'board-select-visibility')]");;
    private By createBtnBy = By.cssSelector("button[data-testid*='create-board']");

    public CreateBoardPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public BoardPage createBoard(String boardName) {
        setBoardName(boardName);
        selectVisibility("public");
        return clickCreateBtn();
    }

    private BoardPage clickCreateBtn() {
        driver.findElement(createBtnBy).click();
        return  new BoardPage(driver);
    }

    private BoardPage selectVisibility(String visibility) {
        driver.findElement(visibilityCmbBoxBy).click();
        switch (visibility){
            case "private" :
                driver.findElement(By.xpath("//div[@id='react-select-3-option-2']/li/div[0]/div[2]/div/span")).click();
                break;
            case "public" :
                driver.findElement(By.xpath("//div[@id='react-select-3-option-2']/li/div[2]/div[2]/div/span")).click();
                //Accept the change
                AdviseChangeVisibilityPage adviseChangePage = new AdviseChangeVisibilityPage(driver);
                driver = adviseChangePage.clickMakePublicBtn();
                break;
            default :
                driver.findElement(By.xpath("//div[@id='react-select-3-option-2']/li/div[1]/div[2]/div/span")).click();

        }

        return clickCreateBtn();
    }

    private void setBoardName(String boardName) {
        WebElement titleBoard = wait.until(ExpectedConditions.presenceOfElementLocated(boardTitleTxtBoxBy));
        titleBoard.sendKeys(boardName);
    }

}
