import CT_CONVERTER.CT_Converter;
import CT_CONVERTER.Simple_CT_Converter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;


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
        Assertions.assertThrows(CT_Converter.WrongFileFormatException.class,
                () -> ctConverter.readFromDataType1("myFile.xxx")
        );
    }

    @Test
    public void testInvalidFileFormatTxtFile() {
        Assertions.assertThrows(CT_Converter.WrongFileFormatException.class,
                () -> ctConverter.readFromDataType2("myFile.xxx", "myFile.bin")
        );
    }

    @Test
    public void testInvalidFileFormatBinFile() {
        Assertions.assertThrows(CT_Converter.WrongFileFormatException.class,
                () -> ctConverter.readFromDataType2("myFile.txt", "myFile.bit")
        );
    }

    @Test
    public void TestSizeOfTwoCtFiles() {
        File dataViewer1 = new File("src/main/resources/sample-files/dataViewer1.ct");
        File dataViewer2 = new File("src/main/resources/sample-files/dataViewer2.ct");
        try {
            ctConverter.readFromDataType1("sample-files/dataViewer1.ct");
            ctConverter.writeDataType1("sample-files/dataViewer2.ct");
        } catch (CT_Converter.WrongFileFormatException ex) {
            throw new RuntimeException(ex);
        }

        System.out.println(dataViewer1.length());
        System.out.println(dataViewer2.length());
        Assertions.assertEquals(dataViewer1.length(),
                dataViewer2.length());

    }


}


