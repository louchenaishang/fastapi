package personal.louchen.fastapi.excerptions;

/**
 * @author Eric.Lou
 */
public class WrongEntityException extends WrongArgException{
    private static final long serialVersionUID = 5346191438364361455L;

    public WrongEntityException(final String argName, final Object actualValue) {
        super(argName, actualValue);
    }

    public WrongEntityException(final String message, final String argName, final Object actualValue) {
        super(message, argName, actualValue);
    }

    public WrongEntityException(final Throwable cause, final String argName, final Object actualValue) {
        super(cause, argName, actualValue);
    }

    public WrongEntityException(final String message, final Throwable cause, final String argName,
                                   final Object actualValue) {
        super(message, cause, argName, actualValue);
    }

    public WrongEntityException(final NameValuePairUtil... args) {
        super(args);
    }

    public WrongEntityException(final String message, final NameValuePairUtil... args) {
        super(message, args);
    }

    public WrongEntityException(final String message, final Throwable cause, final NameValuePairUtil... args) {
        super(message, cause, args);
    }
}
