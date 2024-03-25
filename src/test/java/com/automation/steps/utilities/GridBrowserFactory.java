package com.automation.steps.utilities;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridBrowserFactory {


    /**
     * Creates a Selenium Grid browser instance based on the specified browser name.
     *
     * @param sBrowser the name of the browser to create an instance for
     * @return a WebDriver instance for the specified browser on the Selenium Grid
     * @throws IllegalArgumentException if an invalid browser name is specified
     */
    public WebDriver createSeleniumGridBrowserInstance(String sBrowser){

        // Create desired capabilities for the specified browser
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set browser name and platform based on the specified browser
        switch (sBrowser.toLowerCase()){

            case "chrome":
                capabilities.setBrowserName("chrome");
                capabilities.setPlatform(Platform.WIN11);
                break;

            case "msedge":
                capabilities.setBrowserName("msedge");
                capabilities.setPlatform(Platform.WIN11);
                break;

            case "firefox":
                capabilities.setBrowserName("firefox");
                capabilities.setPlatform(Platform.WIN11);
                break;

            default:
                // Throw an exception if an invalid browser name is specified.
                throw new IllegalArgumentException("Invalid browser specified " + sBrowser);
        }

        // Create and return a RemoteWebDriver instance with the specified capabilities
        return new RemoteWebDriver(capabilities);
    }
}
