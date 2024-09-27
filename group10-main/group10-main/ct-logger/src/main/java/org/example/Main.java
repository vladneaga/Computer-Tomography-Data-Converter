package org.example;




import MyLoggerPackage.LoggerFactory;


/**
 * The {@code Main} class contains methods related to printing and logging.
 */
public class Main {

    /**
     * Prints any output using a logger created with the "console" type.
     */
    public static void printAny() {
        LoggerFactory.createLogger("console", null);
    }

    /**
     * The main method representing the entry point of the application.
     *
     * @param args The command line arguments passed to the program.
     */
    public static void main(String[] args) {

    }
}