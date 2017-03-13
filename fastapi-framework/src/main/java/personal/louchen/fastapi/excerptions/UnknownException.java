package personal.louchen.fastapi.excerptions;

/**
 * 所有未知情况都应抛出此异常。
 *
 * @author Eric.Lou
 */
public class UnknownException extends BaseException {
    public static final String DEFAULT_MSG = "发生了未知错误，请稍后重试或联系客服。";

    private static final long serialVersionUID = 1979687692306854307L;

    //----------------------------------------------------

    public UnknownException() {
        super(DEFAULT_MSG);
    }

    public UnknownException(final String message) {
        super(message);
    }

    public UnknownException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnknownException(final Throwable cause) {
        super(DEFAULT_MSG, cause);
    }

    //----------------------------------------------------
}
