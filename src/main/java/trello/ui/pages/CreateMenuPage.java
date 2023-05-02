package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateMenuPage {
    WebDriver driver;
    WebDriverWait wait;
    private By createBoardOptionBy = By.cssSelector("section[data-testid*='create-menu'] button[data-testid*= 'create-board-button' ]");
    public CreateMenuPage(WebDriver driver){

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public CreateBoardPage clickCreateBoardsBtn() {
        WebElement createBoardOptionElement = wait.until(ExpectedConditions.elementToBeClickable(createBoardOptionBy));
        createBoardOptionElement.click();
        return new CreateBoardPage(driver);
    }
}
