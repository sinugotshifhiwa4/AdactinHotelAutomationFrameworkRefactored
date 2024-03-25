package com.automation.steps.reusableComponents;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfiguration {

    // Static Properties object to hold properties data
    static Properties properties = new Properties();

    // Path to the properties file
    private static final String propertiesPath = System.getProperty("user.dir") + "/src/main/resources/config.properties";

    /**
     * Method to retrieve the value associated with the specified key from the properties file.
     * @param key The key whose value is to be retrieved.
     * @return The value associated with the specified key.
     * @throws IOException if an error occurs while reading the properties file or if the specified key is not found.
     */
    public static String getValueByKey(String key) throws IOException {

        // Initialize FileInputStream to read the properties file
        FileInputStream inputStream = new FileInputStream(propertiesPath);

        // Load properties from the input stream
        properties.load(inputStream);

        // Retrieve the value associated with the specified key
        String value = properties.getProperty(key);

        // Check if the retrieved value is empty or null
        if(StringUtils.isEmpty(value)){
            // If the value is empty or null, throw an IOException with an error message
            throw new IOException("Invalid key specified " + key + " on properties file.");
        }

        // Return the retrieved value
        return value;
    }
}
