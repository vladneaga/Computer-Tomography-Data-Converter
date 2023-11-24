package MyLoggerPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * FileLogger is a logging utility that logs messages to a specified file with timestamp,
 * log level, class name, method name, and the message itself.
 *
 * This class allows for the creation of multiple logger instances, each associated with a
 * specific log file. It ensures that only one logger instance is created for each log file,
 * preventing the unnecessary creation of duplicate loggers for the same file.
 */
public class FileLogger implements LoggerInterface{
    private File file;
    private final PrintWriter printWriter;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd, HH:mm:ss");
    private static Map<File, FileLogger> duplicateStorage= new HashMap<>();
    /**
     * Private constructor to create a new logger instance with the specified log file.
     *
     * @param file The log file to use.
     */
    private FileLogger(File file) {
        this.file = file;
        try {
            this.printWriter = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        duplicateStorage.put(file, this);

    }
    /**
     * Creates a new logger instance with the specified log file.
     *
     * @param file The log file to use.
     * @return A new logger instance.
     */
    public static FileLogger createLogger(File file) {
        if(duplicateStorage.containsKey(file))
            return duplicateStorage.get(file);
        return new FileLogger(file);
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
        printWriter.println(dateFormat.format(new Date()) + " " + logLevel.toString() + ": " +
                className + "." + methodName + ": "+ message);
        printWriter.flush();
    }

    /**
     * Closes the log file.
     */
    public void closeLogFile() {
        printWriter.close();
    }
}

