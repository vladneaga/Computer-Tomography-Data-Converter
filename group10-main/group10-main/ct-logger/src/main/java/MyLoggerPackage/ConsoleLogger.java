package MyLoggerPackage;


import MyLoggerPackage_API.LoggerInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * ConsoleLogger is a simple logging utility that logs messages to the console with timestamp,
 * log level, class name, method name, and the message itself.
 * This class allows for the creation of multiple logger instances, each associated with a
 * specific class. It ensures that only one logger instance is created for each class,
 * preventing the unnecessary creation of duplicate loggers for the same class.
 */
public class ConsoleLogger implements LoggerInterface {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd, HH:mm:ss");
    private static Map<String, ConsoleLogger> duplicateStorage= new HashMap<>();
    /**
     * Private constructor to create a new logger instance with the specified log file.
     */
    private ConsoleLogger(String className) {
        duplicateStorage.put(className, this);
    }
    /**
     * Creates a new logger instance with the specified log file.
     *
     * @return A new logger instance.
     */
    public static ConsoleLogger createLogger() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTrace[2];
        String className = caller.getClassName();
        if(duplicateStorage.containsKey(className))
            return duplicateStorage.get(className);
        return new ConsoleLogger(className);
    }
    /**
     * Logs a message at the specified log level. The log message includes a timestamp,
     * log level, class name, method name, and the message itself.
     *
     * @param logLevel The log level for the message.
     * @param message  The message to log.
     */
    public void log(LogLevel logLevel, String message) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        // The first element (index 0) is the getStackTrace() method itself.
        // The second element (index 1) is the logMessage() method.
        // The third element (index 2) is the method that called logMessage(), which is what we want.
        StackTraceElement caller = stackTrace[2];
        String className = caller.getClassName();
        String methodName = caller.getMethodName();
        System.out.println(dateFormat.format(new Date()) + " " + logLevel.toString() + ": " +
                className + "." + methodName + ": "+ message);
    }


}
