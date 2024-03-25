package com.automation.steps.utilities;

import com.automation.steps.reusableComponents.ExceptionHandler;
import com.automation.steps.reusableComponents.PropertiesConfiguration;
import com.automation.steps.reusableComponents.WebActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase extends WebActions {

    // Initialize ExceptionHandler for handling exceptions
    ExceptionHandler handler = new ExceptionHandler();

    /**
     * Method to launch the application before each test method.
     */
    @BeforeMethod
    public void launchApplication(){

        // Initialize BrowserFactory to create WebDriver instances
        BrowserFactory browserFactory = new BrowserFactory();
        GridBrowserFactory seleniumGrid = new GridBrowserFactory();

        try{
            // Retrieve browser and URL values from properties file
            String sBrowser = PropertiesConfiguration.getValueByKey("browser");
            String sUrl = PropertiesConfiguration.getValueByKey("browserUrl");

            // Set WebDriver instance for the current thread using DriverFactory
            DriverFactory.getInstance().setDriver(browserFactory.createBrowserInstance(sBrowser));
            //DriverFactory.getInstance().setDriver(seleniumGrid.createSeleniumGridBrowserInstance(sBrowser)); just make sure you running selenium grid before you use this grid
            WebDriver driver = DriverFactory.getInstance().getDriver();

            // Maximize the browser window and set implicit wait
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Open the specified URL in the browser
            driver.get(sUrl);

        } catch (Exception e) {
            // If an exception occurs during application launch, handle it
            handler.handleException("launchApplication", e);
        }
    }

    /**
     * Method to perform cleanup after each test method.
     */
    @AfterMethod
    public void tearDown(){
        // Close the browser after each test method
        DriverFactory.getInstance().closeBrowser();
    }
}
