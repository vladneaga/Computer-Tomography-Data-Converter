package MyLoggerPackage;

import MyLoggerPackage_API.LoggerFactoryInterface;
import MyLoggerPackage_API.LoggerInterface;

import java.io.File;

/**
 * LoggerFactory is a utility class responsible for creating instances of different types of loggers.
 * It provides factory methods to create ConsoleLogger and FileLogger instances, ensuring that only
 * one logger instance is created for each associated class or file, preventing unnecessary duplication.
 */
public class LoggerFactory implements LoggerFactoryInterface {
    /**
     * Static factory method that returns a unique instance of a logger avoiding duplicates
     * @param type The type of logger(file or console).
     * @param file  The file if the type of logger is file.
     **/
    public static LoggerInterface createLogger(String type, File file) {
        return switch (type) {
            case "console" -> ConsoleLogger.createLogger();
            case "file" -> FileLogger.createLogger(file);
            default -> null;
        };
    }



    }
