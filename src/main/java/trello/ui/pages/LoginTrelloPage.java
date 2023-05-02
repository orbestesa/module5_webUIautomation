package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginTrelloPage {
    private WebDriver driver;
    private By emailTextBoxBy = By.id("user");
    private By continueBtnBy = By.id("login");
    public LoginTrelloPage(WebDriver driver){
        this.driver = driver;
    }

    public void setEmail(final String userEmail){
        WebElement emailTxtBox = driver.findElement(emailTextBoxBy);
        emailTxtBox.clear();
        emailTxtBox.sendKeys(userEmail);
    }

    public LoginAtlassianPage clickContinueBtn(){
        driver.findElement(continueBtnBy).click();
        return new LoginAtlassianPage(driver);
    }

    public LoginAtlassianPage loginTrello(final  String userEmail){
        setEmail(userEmail);
        return clickContinueBtn();
    }
}
