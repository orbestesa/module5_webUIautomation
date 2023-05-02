package trello.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import trello.ui.pages.CreateBoardPage;

public class MidleContent {
    WebDriver driver;
    private String BOARD_BOX = "//h3[contains(text(),'%s')]/../..//descendant::li[contains(@data-testid,'create-board')]";

    public MidleContent(WebDriver driver) {
        this.driver = driver;
    }

    public CreateBoardPage clickCreateBoardBtnFromWS(String wsName) {
        driver.findElement(By.xpath(String.format(BOARD_BOX,wsName))).click();
        return new CreateBoardPage(driver);
    }
}
