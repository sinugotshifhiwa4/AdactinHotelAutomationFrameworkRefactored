package com.automation.steps.pages;

import com.automation.steps.reusableComponents.ExceptionHandler;
import com.automation.steps.utilities.DriverFactory;
import com.automation.steps.utilities.ExtentFactory;
import com.automation.steps.utilities.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends TestBase {

    ExceptionHandler handler = new ExceptionHandler();

    By usernameTextBox = By.xpath("//*[@id=\"username\"]");
    By passwordTextBox = By.xpath("//*[@id=\"password\"]");
    By loginBtn = By.xpath("//*[@id=\"login\"]");
    By welcomeText = By.xpath("//input[@id='username_show']");
    By errorMessage = By.xpath("//b[contains(.,'Invalid Login details or Your Password might have expired. Click here to reset y')]");

    public void logIn(String Username, String Password){

        try{
            clearObjects(DriverFactory.getInstance().getDriver().findElement(usernameTextBox), "LoginUsernameField");
            sendKeysObjects(DriverFactory.getInstance().getDriver().findElement(usernameTextBox), "LoginUsernameField", Username);

            clearObjects(DriverFactory.getInstance().getDriver().findElement(passwordTextBox), "LoginPasswordField");
            sendKeysObjects(DriverFactory.getInstance().getDriver().findElement(passwordTextBox), "LoginPasswordField", Password);

            Thread.sleep(2000);
            captureScreenshot("LoginCredentials");

            clickObjects(DriverFactory.getInstance().getDriver().findElement(loginBtn), "LoginButton");

            isElementPresentObjects(DriverFactory.getInstance().getDriver().findElement(welcomeText), "WelcomeText");

            Thread.sleep(2000);
            captureScreenshot("SearchHotel");

        } catch (Exception e) {
            handler.handleException("logIn", e);
        }

    }

    public void performLogIn(String Username, String Password){

        try{
            clearObjects(DriverFactory.getInstance().getDriver().findElement(usernameTextBox), "LoginUsernameField");
            sendKeysObjects(DriverFactory.getInstance().getDriver().findElement(usernameTextBox), "LoginUsernameField", Username);

            clearObjects(DriverFactory.getInstance().getDriver().findElement(passwordTextBox), "LoginPasswordField");
            sendKeysObjects(DriverFactory.getInstance().getDriver().findElement(passwordTextBox), "LoginPasswordField", Password);

            clickObjects(DriverFactory.getInstance().getDriver().findElement(loginBtn), "LoginButton");


            boolean loginSuccessful = isLoginSuccessful(welcomeText);

            // Optionally, you can perform further actions based on login status
            if (loginSuccessful) {
                System.out.println("Login was successful");
            } else {
                System.out.println("Login was not successful");
            }

        } catch (Exception e) {
            handler.handleException("logIn", e);
        }
    }
}
