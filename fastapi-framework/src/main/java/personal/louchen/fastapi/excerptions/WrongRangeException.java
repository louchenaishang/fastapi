package personal.louchen.fastapi.excerptions;


import personal.louchen.fastapi.utils.NameValuePairUtil;

/**
 * @author Eric.Lou
 */
public class WrongRangeException extends WrongArgException{
    private static final long serialVersionUID = 2645094450735183343L;

    public WrongRangeException(final String argName, final Object actualValue) {
        super(argName, actualValue);
    }

    public WrongRangeException(final String message, final String argName, final Object actualValue) {
        super(message, argName, actualValue);
    }

    public WrongRangeException(final Throwable cause, final String argName, final Object actualValue) {
        super(cause, argName, actualValue);
    }

    public WrongRangeException(final String message, final Throwable cause, final String argName,
                                  final Object actualValue) {
        super(message, cause, argName, actualValue);
    }

    public WrongRangeException(final NameValuePairUtil... args) {
        super(args);
    }

    public WrongRangeException(final String message, final NameValuePairUtil... args) {
        super(message, args);
    }

    public WrongRangeException(final String message, final Throwable cause, final NameValuePairUtil... args) {
        super(message, cause, args);
    }
}
