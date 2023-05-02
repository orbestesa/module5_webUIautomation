package trello.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import trello.ui.pages.CreateMenuPage;

import java.time.Duration;

public class TopMenu {
    WebDriver driver;
    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    private By accountMenuBtnBy = By.cssSelector("[data-testid*='member-menu-button']");
    private String EMAIL_TEXT_PATH = "//div[text()='%s']";
    private By emailBy = By.xpath("//div[@data-testid='account-menu-account-section']//div[contains(text(),'@')]");
    private By createMenuBtnBy = By.cssSelector("[data-testid *= 'create-menu']");

    public TopMenu(WebDriver driver){
        this.driver = driver;
    }

    public void clickAccountMenuBtn(){
        driver.findElement(accountMenuBtnBy).click();
    }

    public boolean userEmailDisplay(String email){
        By emailBy = By.xpath(String.format(EMAIL_TEXT_PATH,email));
        return driver.findElement(emailBy).isDisplayed();
    }

    public String getUserEmail(){
        return driver.findElement(emailBy).getText();
    }

    public CreateMenuPage openCreateMenu() {
        //WebElement createMenuBtn = wait.until(ExpectedConditions.elementToBeClickable(createMenuBtnBy));
        driver.findElement(createMenuBtnBy).click();
        return new CreateMenuPage(driver);
    }
}
