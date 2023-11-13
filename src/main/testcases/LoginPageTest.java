package main.testcases;

import main.browserutility.Browser;
import main.helper.CommonUtility;
import main.helper.ConfigReader;
import main.helper.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import main.pages.PagesFactory;
import main.pages.LoginPage;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LoginPageTest extends BaseTest{
    LoginPage loginPage;

    /***
     * Implementing test case to validate login functionality
     */
//    @Test(priority = 1)
//    public void checkUserSuccessfullyLogsInForValidEmailValidPassword(){
//        CommonUtility.logCreateTest("checkUserSuccessfullyLogsInForValidEmailValidPassword");
//
//        loginPage = PagesFactory.getLoginPage();
//
//        loginPage.clickOnLoginButton();
//        Logger.endTestCase("Login_functionality");
//
//    }
}
