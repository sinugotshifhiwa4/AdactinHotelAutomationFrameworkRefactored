package com.automation.steps.utilities;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {

    // Singleton instance of ExtentFactory
    private static final ExtentFactory instance = new ExtentFactory();

    // Private constructor to prevent instantiation from outside the class
    private ExtentFactory(){}

    /**
     * Method to retrieve the singleton instance of ExtentFactory.
     * @return The singleton instance of ExtentFactory.
     */
    public static ExtentFactory getInstance(){
        return instance;
    }

    // ThreadLocal variable to store ExtentTest instances per thread
    private final ThreadLocal<ExtentTest> threadLocalExtent = new ThreadLocal<>();

    /**
     * Method to set the ExtentTest instance for the current thread.
     * @param extent The ExtentTest instance to be set.
     */
    public void setExtent(ExtentTest extent){
        threadLocalExtent.set(extent);
    }

    /**
     * Method to retrieve the ExtentTest instance for the current thread.
     * @return The ExtentTest instance for the current thread.
     */
    public ExtentTest getExtent(){
        return threadLocalExtent.get();
    }

    /**
     * Method to remove the ExtentTest instance for the current thread.
     */
    public void removeExtentObject(){
        threadLocalExtent.remove();
    }
}
