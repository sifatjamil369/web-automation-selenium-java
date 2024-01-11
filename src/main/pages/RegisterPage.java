package main.pages;

import main.helper.CommonUtility;
import org.openqa.selenium.By;

public class RegisterPage extends BaseClass{

    By registerLoginButtonLocator = By.xpath("//u[normalize-space()='Register / Login']");
    By nameFieldLocator = By.xpath("//input[@data-qa='signup-name']");
    By emailFieldLocator = By.xpath("//input[@data-qa='signup-email']");
    By signupButtonLocator = By.xpath("//button[@data-qa='signup-button']");
    By passwordFieldLocator = By.xpath("//input[@data-qa='password']");
    By firstNameFieldLocator = By.xpath("//input[@data-qa='first_name']");
    By lastNameFieldLocator = By.xpath("//input[@data-qa='last_name']");
    By addressFieldLocator = By.xpath("//input[@data-qa='address']");
    By stateFieldLocator = By.xpath("//input[@data-qa='state']");
    By cityFieldLocator = By.xpath("//input[@data-qa='city']");
    By zipcodeFieldLocator = By.xpath("//input[@data-qa='zipcode']");
    By mobileFieldLocator = By.xpath("//input[@data-qa='mobile_number']");
    By createAccountButtonLocator = By.xpath("//button[@data-qa='create-account']");
    By accountCreatedMessageLocator = By.xpath("//h2[@data-qa='account-created']");
    By continueButtonLocator = By.xpath("//a[@data-qa='continue-button']");

    /*
    Clicks on the Register button
     */
    public void clickOnRegisterButton() {
        try {
            clickOn(registerLoginButtonLocator, 120, "'Register' button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Fills in the necessary details and registers a user
     */
    public void newUserSignUp(String name, String email, String password, String firstName, String lastName, String address, String state, String city, String zipcode, String mobileNumber) {
        try {
            sendKeys(nameFieldLocator, name, "Name");
            sendKeys(emailFieldLocator, email, "Email");
            clickOn(signupButtonLocator, 120, "Signup button");
            sendKeys(passwordFieldLocator, password, "Password");
            sendKeys(firstNameFieldLocator, firstName, "First name");
            sendKeys(lastNameFieldLocator, lastName, "Last name");
            sendKeys(addressFieldLocator, address, "Address");
            sendKeys(stateFieldLocator, state, "State");
            sendKeys(cityFieldLocator, city, "City");
            sendKeys(zipcodeFieldLocator, zipcode, "Zipcode");
            sendKeys(mobileFieldLocator, mobileNumber, "Mobile number");
            clickOn(createAccountButtonLocator, 120, "Create Account button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Checks if the user is registered using the success message
     */
    public Boolean isUserRegistered() {
        try {
            return isExpectedElementVisible(accountCreatedMessageLocator, 120, "'Account Created' successfully");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }

    /*
    Clicks on the Continue button
     */
    public void clickOnContinueButton() {
        try {
            clickOn(continueButtonLocator, 120, "'Continue' button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }
}
