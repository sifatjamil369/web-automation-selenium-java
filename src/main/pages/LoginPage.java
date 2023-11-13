package main.pages;

import org.openqa.selenium.By;

public class LoginPage extends BaseClass{
    By loginButtonLocator = By.xpath("//div[@class='nsm7Bb-HzV7m-LgbsSe-MJoBVe']");

    public void clickOnLoginButton(){
        clickWithJavaScript(loginButtonLocator);
//        clickOn(loginButtonLocator, "Clicked on the 'Sign in with Google' button");
    }
}
