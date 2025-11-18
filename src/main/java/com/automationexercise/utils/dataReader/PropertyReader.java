package com.automationexercise.utils.dataReader;

import com.automationexercise.utils.logs.LogsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Properties;

public class PropertyReader {
    // Load Properties function to load all data exists on .properties files into system properties
    public static Properties loadProperties() {
        try {
            Properties properties = new Properties();
            Collection<File> propertiesFiles;
            propertiesFiles = FileUtils.listFiles(new File("src/main/resources"), new String[]{"properties"}, true); //get all files with extension properties
            propertiesFiles.forEach(file -> {
                try {
                    properties.load(new FileInputStream(file));
                } catch (Exception e) {
                    LogsManager.error("Error loading properties file:", file.getName(), e.getMessage());
                }
                System.getProperties().putAll(properties);
                properties.putAll(System.getProperties());

            });
            LogsManager.info("Properties file loaded successfully");
            return properties;
        } catch (Exception e) {
            LogsManager.error("Error loading properties file", e.getMessage());
            return null;
        }
    }

    public static String getProperty(String key) {
        String value = null;

        try {
            value = System.getProperty(key);

            if (value == null || value.isBlank()) {
                LogsManager.warn("Missing or empty property for key: " + key);
                return "";
            }

            return value;

        } catch (Exception e) {
            LogsManager.error("Error retrieving property for key: " + key + " | " + e.getMessage());
            return "";
        }
    }
}
