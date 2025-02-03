package com.example.farmcoop.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties properties = new Properties();

    // Loading the file once in a static block
    static {
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                LoggerUtil.error("Unable to find configuration file: " + CONFIG_FILE, null);
            } else {
                properties.load(input);
            }
        } catch (IOException e) {
            LoggerUtil.error("Error loading configuration file: " + CONFIG_FILE, e);
        }
    }

    // Method to retrieve an int value
    public static int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            LoggerUtil.warn("Invalid integer format for key: " + key);
            return defaultValue;
        }
    }
}
