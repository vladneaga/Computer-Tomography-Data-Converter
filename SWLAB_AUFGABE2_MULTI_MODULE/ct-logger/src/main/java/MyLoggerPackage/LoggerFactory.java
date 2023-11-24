package MyLoggerPackage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * LoggerFactory is a utility class responsible for creating instances of different types of loggers.
 * It provides factory methods to create ConsoleLogger and FileLogger instances, ensuring that only
 * one logger instance is created for each associated class or file, preventing unnecessary duplication.
 */
public class LoggerFactory implements LoggerFactoryInterface {
    /**
     * Static factory method that returns a unique instance of a logger avoiding duplicates
     * @param type The type of logger(file or console).
     * @param path  The path to the file if the type of logger is file.
     **/
    public static LoggerInterface createLogger(String type, String path) {
        return switch (type) {
            case "console" -> ConsoleLogger.createLogger();
            case "file" -> FileLogger.createLogger(new File(path));
            default -> null;
        };
    }



    }
