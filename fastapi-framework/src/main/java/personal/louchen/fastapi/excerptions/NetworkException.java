package personal.louchen.fastapi.excerptions;

/**
 * 网络异常。
 *
 * @author Eric.Lou
 */
public class NetworkException extends BaseException {

    private static final long serialVersionUID = -3541694000269634540L;
    public static final String DEFAULT_MSG = "网络异常，请稍后重试或联系客服。";

    public NetworkException() {
        super(DEFAULT_MSG);
    }

    public NetworkException(final String message) {
        super(message, NEXT_STEP_IDS_OF_RETRY_OR_REPORT_BUG);
    }

    public NetworkException(final Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public NetworkException(final String message, final Throwable cause) {
        super(message, cause, NEXT_STEP_IDS_OF_RETRY_OR_REPORT_BUG);
    }
}
