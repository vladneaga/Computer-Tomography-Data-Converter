import CT_CONVERTER_API.CT_Converter;
import CT_CONVERTER.Simple_CT_Converter;
import CT_CONVERTER_API.WrongFileFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Converter_Test {
    File fileCt;
    File fileTxt;
    File fileBin;
    CT_Converter ctConverter;

    @BeforeEach
    public void setUp() {
        ctConverter = new Simple_CT_Converter();
        fileCt = new File("testFile.ct");
        fileTxt = new File("testFile.txt");
        fileBin = new File("testFile.bin");
    }

    @Test
    public void testFileCreation() {
        System.out.println(fileCt.getAbsolutePath());
        System.out.println(fileTxt.getAbsolutePath());
        System.out.println(fileBin.getAbsolutePath());
    }

    @Test
    public void testInvalidFileFormatCtFile() {
        Assertions.assertThrows(WrongFileFormatException.class,
                () -> ctConverter.readFromDataType1(new File("myFile.xxx"))
        );
    }

    @Test
    public void testInvalidFileFormatTxtFile() {
        Assertions.assertThrows(WrongFileFormatException.class,
                () -> ctConverter.readFromDataType2(new File("myFile.xxx"), new File("myFile.bin"))
        );
    }

    @Test
    public void testInvalidFileFormatBinFile() {
        Assertions.assertThrows(WrongFileFormatException.class,
                () -> ctConverter.readFromDataType2(new File("myFile.txt"), new File("myFile.bit"))
        );
    }


    @Test
    public void testName() {
        Path ct1FilePath =  Paths.get("src", "main", "resources", "sample-files", "dataViewer1.ct");
        Path ct2FilePath =  Paths.get("src", "main", "resources", "sample-files", "fileToBeFulled.ct");
        File ctFile = new File(ct1FilePath.toString());
        File ctTestFile = new File(ct2FilePath.toString());
        Simple_CT_Converter simpleCtConverter1 = new Simple_CT_Converter();

        try {
            simpleCtConverter1.readFromDataType1(ctFile);
            String name1 = simpleCtConverter1.getPatient().getName();
            simpleCtConverter1.writeDataType1(ctTestFile);
            simpleCtConverter1.readFromDataType1(ctTestFile);
            String name2 = simpleCtConverter1.getPatient().getName();
            Assertions.assertEquals(name1, name2);
        } catch (WrongFileFormatException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testIZ() {
        Path ct1FilePath =  Paths.get("src", "main", "resources", "sample-files", "dataViewer1.ct");
        Path ct2FilePath =  Paths.get("src", "main", "resources", "sample-files", "fileToBeFulled.ct");
        File ctFile = new File(ct1FilePath.toString());
        File ctTestFile = new File(ct2FilePath.toString());;
        Simple_CT_Converter simpleCtConverter1 = new Simple_CT_Converter();

        try {
            simpleCtConverter1.readFromDataType1(ctFile);
            String IZ1 = simpleCtConverter1.getPatient().getIZ();
            simpleCtConverter1.writeDataType1(ctTestFile);
            simpleCtConverter1.readFromDataType1(ctTestFile);
            String IZ2 = simpleCtConverter1.getPatient().getIZ();
            Assertions.assertEquals(IZ1, IZ2);
        } catch (WrongFileFormatException e) {
            throw new RuntimeException(e);
        }
    }



}


