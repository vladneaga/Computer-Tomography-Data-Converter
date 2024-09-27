package CT_CONVERTER_API;


/** The {@code CT_Converter} interface defines methods for reading and writing
        * medical data in different formats.
        *
 * This interface provides methods to read and write data of two specific types:
        * Musterstadt1 and Musterstadt2. The methods allow interaction with files in the
        * predefined format, and 2 types of storage((.ct) + (.txt; .bin))
        *
        * The file format is of the following type:
       name	    ${...}
       birth	${...}
       weight	${...}
       height	${...}
       IZ	    ${...}
       image	${...}
       DATA
      ${Integer value of the X dimension of the CT object}
      ${Integer value of the Y dimension of the CT object}
      ${Integer value of the Z dimension of the CT object}
      *${The ct object data itself}
 * Implementations of this interface should handle the conversion and
 * manipulation of data according to the specified data types and file formats.
         *
        *
        * @author Vlad Neaga
        * @author Abdul Satar Amiri
        * @version 1.0
        */
public interface CT_Converter extends CT_Reader, CT_Writer{

}
