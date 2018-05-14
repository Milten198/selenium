package util.exceptions;

/**
 * This exception is use when on list on GUI is more then one element with the same name which we look for
 */
public class CollectionSizeException extends RuntimeException {
    public CollectionSizeException(String message) {
        super(message);
    }
}
