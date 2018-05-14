package util.exceptions;

/**
 * This exception it use when in data base is more records then we expected
 */

public class UnexpectedRecordsException extends RuntimeException {
    public UnexpectedRecordsException(String message) {
        super(message);
    }
}
