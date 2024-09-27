package MyLoggerPackage_API;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
/**
 * LoggerInterface is an interface defining the contract for logger implementations.
 * It includes methods for creating logger instances, logging messages, and closing log files.
 */
public interface LoggerInterface {
    /**
     * A {@code File} object used for file manipulation.
     * <p>
     * This is a null-initialized field, and it is recommended to assign an actual {@code File} instance before usage.
     * </p>
     */
    File file = null;
    /**
     * A {@code PrintWriter} object used for writing formatted text to a file.
     * <p>
     * This is a null-initialized field, and it is recommended to assign an actual {@code PrintWriter} instance before usage.
     * </p>
     */
    PrintWriter printWriter = null;
    /**
     * A {@code SimpleDateFormat} instance used for date formatting.
     * <p>
     * This is a static final field, indicating that it is a constant and shared among all instances of the class.
     * </p>
     */
    static final SimpleDateFormat dateFormat = null;
    /**
     * Enum representing log levels.
     */
    public enum LogLevel {
        /**
         * Represents the highest severity level, indicating an error.
         */
        ERROR,

        /**
         * Represents a moderate severity level, indicating a warning.
         */
        WARN,

        /**
         * Represents the lowest severity level, providing general information.
         */
        INFO
    }
    /**
     * Creates a new logger instance with the specified log file.
     *
     * @param file The log file to use.
     * @return A new logger instance.
     */
    public static LoggerInterface createLogger(File file) {
        return null;
    }
    private void a() {}
    /**
     * Logs a message at the specified log level.
     *
     * @param logLevel The log level for the message.
     * @param message  The message to log.
     */
    public default void log(LogLevel logLevel, String message) {}
    /**
     * Closes the log file.
     */
    public static void closeLogFile() {}

}
