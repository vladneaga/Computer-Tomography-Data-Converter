package MyLoggerPackage;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
/**
 * LoggerInterface is an interface defining the contract for logger implementations.
 * It includes methods for creating logger instances, logging messages, and closing log files.
 */
public interface LoggerInterface {
    File file = null;
    PrintWriter printWriter = null;
    static  final SimpleDateFormat dateFormat = null;
    /**
     * Enum representing log levels.
     */
    public enum LogLevel {
        ERROR, WARN, INFO
    }
    /**
     * Creates a new logger instance with the specified log file.
     *
     * @param file The log file to use.
     * @return A new logger instance.
     */
    public static FileLogger createLogger(File file) {
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
