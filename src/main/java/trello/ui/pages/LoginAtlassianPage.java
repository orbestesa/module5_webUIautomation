package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginAtlassianPage {
    private WebDriver driver;
    private By passwordTextBoxBy = By.cssSelector("input[id='password'][aria-labelledby*='password");
    private By loginInBtnBy = By.id("login-submit");
    private WebDriverWait wait;
    public LoginAtlassianPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitUntilPageIsLoaded();
    }

    private void waitUntilPageIsLoaded(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginInBtnBy));
    }
    public LoginAtlassianPage setPassword(String password){
        WebElement passwordTextBox = driver.findElement(passwordTextBoxBy);
        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);
        return this;
    }

    public BoardsPage clickLoginBtn(){
        driver.findElement(loginInBtnBy).click();
        return new BoardsPage(driver);
    }

    public BoardsPage loginToAtlassian(String password){
        setPassword(password);
        return clickLoginBtn();
    }
}
