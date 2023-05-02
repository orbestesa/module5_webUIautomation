package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BoardPage {
    WebDriver driver;
    private By titleBoardLabelBy = By.cssSelector("div[data-testid='board-name-container'] h1");

    public BoardPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getBoardTitle() {
        return  driver.findElement(titleBoardLabelBy).getText();
    }
}
