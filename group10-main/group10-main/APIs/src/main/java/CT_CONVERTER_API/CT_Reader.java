package CT_CONVERTER_API;

import java.io.File;
/** The {@code CT_Reader} interface defines methods for reading
 * medical data in different formats.
 *
 * This interface provides methods to read data of two specific types:
 * Musterstadt1 and Musterstadt2. The methods allow reading from files in predefined
 * formats, with files such as text  (.txt), binary files (.bin), and Computed
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
public interface CT_Reader {
    /**
     * Reads data from a file of DataType1 format.
     *
     * @param file file containing DataType1 data
     * @throws WrongFileFormatException If the file format is wrong.
     */
    public void readFromDataType1(File file) throws WrongFileFormatException;
    /**
     * Reads data from files of DataType2 format, consisting of a text file and a
     * binary file.
     *
     * @param txtFile the text file containing DataType2 data
     * @param binFile the binary file containing DataType2 data
     * @throws WrongFileFormatException If the file formats are wrong.
     */
    public void readFromDataType2(File txtFile, File binFile) throws WrongFileFormatException;

}
