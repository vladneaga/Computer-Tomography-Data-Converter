package CT_CONVERTER_API;

/**
 * Exception thrown to indicate that a file has an unexpected or incorrect format.
 * This exception is typically used when processing files and encountering a format
 * that does not match the expected structure or content.
 */
public class WrongFileFormatException extends Exception {

    /**
     * Constructs a new {@code WrongFileFormatException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public WrongFileFormatException(String message) {
        super(message);
    }
}