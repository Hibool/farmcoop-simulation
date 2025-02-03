package com.example.farmcoop.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

// Utility class for logging messages
public class LoggerUtil {
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getSimpleName());

    static {
        try {
            // Save logs in text file
            String logDir = "logs";
            Files.createDirectories(Paths.get(logDir));

            String datePattern = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String logFilename = logDir + "/farmcoop_simulation_logs_" + datePattern + ".txt";

            FileHandler fileHandler = new FileHandler(logFilename);
            SimpleFormatter simpleFormatter = new SimpleFormatter() {
                @Override
                public synchronized String format(LogRecord record) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");
                    String timestamp = sdf.format(new Date(record.getMillis()));
                    return String.format("%s %s %s%n", timestamp, record.getLoggerName(), record.getMessage());                }
            };
            fileHandler.setFormatter(simpleFormatter); // Format the logs in a simple way (ex: "date - simple filename - level - message")
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL); // Make sure all log levels are saved
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to initialize file handler for logging", e);
        }
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warning(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.log(Level.SEVERE, message, throwable);
    }
}
