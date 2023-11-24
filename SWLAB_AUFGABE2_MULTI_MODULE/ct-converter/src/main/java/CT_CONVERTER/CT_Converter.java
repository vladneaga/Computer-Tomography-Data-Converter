package CT_CONVERTER;


/* The {@code CT_Converter} interface defines methods for reading and writing
        * medical data in different formats.
        *
 * This interface provides methods to read and write data of two specific types:
        * DataType1 and DataType2. The methods allow interaction with files in various
        * formats, such as text files (.txt), binary files (.bin), and Computed
        * Tomography (CT) files.
        *
 * Implementations of this interface should handle the conversion and
 * manipulation of data according to the specified data types and file formats.
         *
        *
        * @author Vlad Neaga
        * @author Abdul Satar Amiri
        * @version 1.0
        */
public interface CT_Converter {

    /**
     * Reads data from a file of DataType1 format.
     *
     * @param ctFile the path to the CT file containing DataType1 data
     * @throws WrongFileFormatException If the file format is wrong.
     */
    public void readFromDataType1(String ctFile) throws WrongFileFormatException;

    /**
     * Reads data from files of DataType2 format, consisting of a text file and a
     * binary file.
     *
     * @param txtFile the path to the text file containing DataType2 data
     * @param binFile the path to the binary file containing DataType2 data
     * @throws WrongFileFormatException If the file formats are wrong.
     */
    public void readFromDataType2(String txtFile, String binFile) throws WrongFileFormatException;

    /**
     * Writes data of DataType1 format to a CT file.
     *
     * @param ctFile the path to the CT file to which DataType1 data will be written
     * @throws WrongFileFormatException If the file format is wrong.
     */
    public void writeDataType1(String ctFile) throws WrongFileFormatException;

    /**
     * Writes data of DataType2 format to both a text file and a binary file.
     *
     * @param txtFile the path to the text file to which DataType2 text data will
     *                be written
     * @param binFile the path to the binary file to which DataType2 binary data will be written
     * @throws WrongFileFormatException If the file formats are wrong.
     */
    public void writeDataType2(String txtFile, String binFile) throws WrongFileFormatException;
    public class WrongFileFormatException extends Exception {
        public WrongFileFormatException(String message) {
            super(message);
        }
    }
}
