package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdviseChangeVisibilityPage {
    WebDriver driver;
    private By makePublicBtnBy = By.xpath("//div[@class='atlaskit-portal']/section/div/div/button");
    public AdviseChangeVisibilityPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver clickMakePublicBtn() {
        driver.findElement(makePublicBtnBy).click();
        return driver;
    }
}
