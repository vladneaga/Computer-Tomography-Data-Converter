import MyLoggerPackage.LoggerFactory;
import MyLoggerPackage.LoggerFactoryInterface;
import MyLoggerPackage.LoggerInterface;
import MyLoggerPackage.FileLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyLoggerTest {
    private FileLogger logger;
    private File logFile;
    BufferedReader fileReader;
    /*!
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //BITTE TESTS SEPARAT LAUFEN LASSEN. DANKE!!!

     */
    @BeforeEach
    public void setUp() throws FileNotFoundException {
        logFile = new File("aaa.txt");
        logger = (FileLogger) LoggerFactory.createLogger("file", logFile.getName());
        fileReader = new BufferedReader(new FileReader("aaa.txt"));
    }

    @AfterEach
    public void tearDown() {
        if (logger != null) {
            logger.closeLogFile();
        }
        if (logFile.exists()) {
            logFile.delete();
        }
    }
    /*!
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //BITTE TESTS SEPARAT LAUFEN LASSEN. DANKE!!!

     */
    @Test
    public void testLogInfo() {
        logger.log(LoggerInterface.LogLevel.INFO, "Info message");
        assertTrue(logFile.exists());
    }
    /*!
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //BITTE TESTS SEPARAT LAUFEN LASSEN. DANKE!!!

     */
    @Test
    public void testLogWarning() {
        logger.log(LoggerInterface.LogLevel.WARN, "Warning message");
        assertTrue(logFile.exists());
    }
    /*!
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //BITTE TESTS SEPARAT LAUFEN LASSEN. DANKE!!!

     */
    @Test
    public void testLogError() {
        logger.log(LoggerInterface.LogLevel.ERROR, "Error message");
        assertTrue(logFile.exists());
    }
    /*!
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //BITTE TESTS SEPARAT LAUFEN LASSEN. DANKE!!!

 */
    @Test
    public void testLogInfoText() throws IOException {
        LoggerInterface loggerInfo = LoggerFactory.createLogger("file", "bbb.txt");
        loggerInfo.log(LoggerInterface.LogLevel.INFO, "My Information");
        fileReader = new BufferedReader(new FileReader("bbb.txt"));
        boolean foundLine = false;
        String myLine = "";
        for(String line; (line = fileReader.readLine()) != null; ) {
            // process the line.
            if(line.contains("INFO: MyLoggerTest.testLogInfoText: My Information")) {
                //System.out.println(line);
                foundLine = true;
                myLine = line;
            }
        }
        System.out.println(myLine);
        assertTrue(myLine.contains("INFO: MyLoggerTest.testLogInfoText: My Information"), myLine );
    }
    /*!
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //BITTE TESTS SEPARAT LAUFEN LASSEN. DANKE!!!

 */
    @Test
    public void testLogWarnText() throws IOException {
        LoggerInterface loggerWarn = LoggerFactory.createLogger("file", "ccc.txt");
        loggerWarn.log(LoggerInterface.LogLevel.INFO, "My Information");
        fileReader = new BufferedReader(new FileReader("ccc.txt"));
        loggerWarn.log(LoggerInterface.LogLevel.WARN, "My Warning");
        loggerWarn.log(LoggerInterface.LogLevel.WARN, "My Warning 2");
        boolean foundLine = false;
        String myLine = "";
        for(String line; (line = fileReader.readLine()) != null; ) {
            // process the line.
            if(line.contains("WARN: MyLoggerTest.testLogWarnText: My Warning")) {
                System.out.println(line);
                foundLine = true;
            }
        }

        assertTrue(foundLine);
    }
    /*!
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
   //BITTE TESTS SEPARAT LAUFEN LASSEN. DANKE!!!

*/
    @Test
    public void testSameLogger() {
        assertEquals(logger, LoggerFactory.createLogger("file", "aaa.txt"));
    }
}