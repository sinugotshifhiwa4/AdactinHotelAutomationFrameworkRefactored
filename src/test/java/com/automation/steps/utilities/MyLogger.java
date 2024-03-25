package com.automation.steps.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.io.File;


public class MyLogger {

    private static final Logger logger = LogManager.getLogger();

    public static synchronized void startTestCase(String sTestCaseName) {
        sTestCaseName = sTestCaseName.replaceAll("[^a-zA-Z0-9]", "_").replaceAll("_+", "_");
        startLog(System.getProperty("user.dir"), sTestCaseName);
        logger.info("\n\nExecution Started: {}", sTestCaseName);
    }

    public static void endTestCase(String sTestCaseName) {
        logger.info("\n\nExecution Ended: {}", sTestCaseName);
    }

    public static void startLog(String dirPath, String testCaseName) {
        int noOfFiles = 0;

        File dir = new File(dirPath);
        if (dir.exists()) {
            int count = 0;
            for (File file : dir.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".log") && file.getName().contains(testCaseName)) {
                    count++;
                }
            }
            noOfFiles = count;
        }
        noOfFiles++;
        String logFilename = testCaseName + "_" + noOfFiles;
        ThreadContext.put("LogFilename", logFilename);
    }

    public static Logger getCurrentLog() {
        return logger;
    }

    public static String getCallInfo() {
        String callInfo;
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

        callInfo = String.join(" ", className + ":", methodName, "- on line", String.valueOf(lineNumber), "→ ");
        return callInfo;
    }

    public static void trace(Object message) {
        getCurrentLog().trace(getCallInfo() + message);
    }

    public static void trace(Object message, Throwable t) {
        getCurrentLog().trace(getCallInfo() + message, t);
    }

    public static void debug(Object message) {
        getCurrentLog().debug(getCallInfo() + message);
    }

    public static void debug(Object message, Throwable t) {
        getCurrentLog().debug(getCallInfo() + message, t);
    }

    public static void error(Object message) {
        getCurrentLog().error(getCallInfo() + message);
    }

    public static void error(Object message, Throwable t) {
        getCurrentLog().error(getCallInfo() + message, t);
    }

    public static void fatal(Object message) {
        getCurrentLog().fatal(getCallInfo() + message);
    }

    public static void fatal(Object message, Throwable t) {
        getCurrentLog().fatal(getCallInfo() + message, t);
    }

    public static void info(Object message) {
        getCurrentLog().info(getCallInfo() + message.toString());
    }

    public static void info(Object message, Throwable t) {
        getCurrentLog().info(getCallInfo() + message, t);
    }

    public static void warn(Object message) {
        getCurrentLog().warn(getCallInfo() + message);
    }

    public static void warn(Object message, Throwable t) {
        getCurrentLog().warn(getCallInfo() + message, t);
    }
}
