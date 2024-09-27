package CT_CONVERTER_API;

import java.io.File;

/** The {@code CT_Writer} interface defines methods for writing
 * medical data in different formats.
 *
 * This interface provides methods to write data of two specific types:
 * Musterstadt1 and Musterstadt2. The methods allow writing to with files in various
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
public interface CT_Writer {
    /**
     * Writes data of DataType1 format to a CT file.
     *
     * @param ctFile the CT file to which DataType1 data will be written
     * @throws WrongFileFormatException If the file format is wrong.
     */
    public void writeDataType1(File ctFile) throws WrongFileFormatException;
    /**
     * Writes data of DataType2 format to both a text file and a binary file.
     *
     * @param txtFile the text file to which DataType2 text data will
     *                be written
     * @param binFile the binary file to which DataType2 binary data will be written
     * @throws WrongFileFormatException If the file formats are wrong.
     */
    public void writeDataType2(File txtFile, File binFile) throws WrongFileFormatException;

}
