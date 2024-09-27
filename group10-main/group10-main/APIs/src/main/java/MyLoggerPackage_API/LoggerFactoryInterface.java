package MyLoggerPackage_API;

import java.io.File;

/**
 * LoggerFactoryInterface is an interface that defines factory methods for creating instances
 * of LoggerInterface implementations. Implementing classes provide concrete implementations
 * for creating console and file loggers.
 */
public interface LoggerFactoryInterface {
    /**
     * Factory method to create a ConsoleLogger instance.
     *
     * @return A new ConsoleLogger instance.
     */
    static LoggerInterface createConsoleLogger() {
        return null; // Actual implementation to be provided by implementing classes.
    }

    /**
     * Factory method to create a FileLogger instance with the specified log file.
     *
     * @param file the log file to use.
     * @return A new FileLogger instance associated with the specified log file.
     */
    static LoggerInterface createFileLogger(File file) {
        return null; // Actual implementation to be provided by implementing classes.
    }
}
