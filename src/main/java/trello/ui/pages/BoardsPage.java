package trello.ui.pages;

import org.openqa.selenium.WebDriver;
import trello.ui.components.MidleContent;
import trello.ui.components.TopMenu;

public class BoardsPage {

    private WebDriver driver;
    private TopMenu topMenu;
    private MidleContent midleContent;

    public BoardsPage(WebDriver driver) {
        this.driver = driver;
        topMenu = new TopMenu(driver);
        midleContent = new MidleContent(driver);
    }

    public TopMenu getTopMenu(){
        return topMenu;
    }

    public MidleContent getMiddleContent() {
        return midleContent;
    }
}
