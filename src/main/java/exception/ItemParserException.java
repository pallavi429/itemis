package exception;

public class ItemParserException extends RuntimeException {

    public ItemParserException(String expected) {
        super(expected);
    }
}
