package com.automation.steps.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

    /**
     * Method to create a WebDriver instance based on the specified browser name.
     * @param sBrowser Name of the browser (e.g., "chrome", "firefox", "edge").
     * @return WebDriver instance corresponding to the specified browser.
     * @throws IllegalArgumentException if an invalid browser name is specified.
     */
    public WebDriver createBrowserInstance(String sBrowser){

        WebDriver driver;

        switch (sBrowser.toLowerCase()){

            case "chrome":
                // Configure Chrome options, e.g., incognito mode.
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                // Create a ChromeDriver instance with the specified options.
                driver = new ChromeDriver(chromeOptions);
                break;

            case "msedge":
                // Configure Edge options, e.g., headless mode.
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                // Create an EdgeDriver instance with the specified options.
                driver = new EdgeDriver(edgeOptions);
                break;

            case "firefox":
                // Configure Firefox options, e.g., incognito mode.
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--incognito");
                // Create a FirefoxDriver instance with the specified options.
                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                // Throw an exception if an invalid browser name is specified.
                throw new IllegalArgumentException("Invalid browser specified " + sBrowser);
        }

        // Return the created WebDriver instance.
        return driver;
    }
}
