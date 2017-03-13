package personal.louchen.fastapi.excerptions;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Eric.Lou
 */
public class NeedLoginException extends BaseException {
    public static final String DEFAULT_MSG = "请登录。";

    /**
     * 下一步可以去登录
     */
    public static final Collection<String> NEXT_STEP_IDS_OF_LOGIN = Collections.singleton("login");

    private static final long serialVersionUID = -5871718344537366804L;

    //----------------------------------------------------

    public NeedLoginException() {
        super(DEFAULT_MSG, NEXT_STEP_IDS_OF_LOGIN);
    }

    public NeedLoginException(final String message) {
        super(message, NEXT_STEP_IDS_OF_LOGIN);
    }

    public NeedLoginException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NeedLoginException(final Throwable cause) {
        super(DEFAULT_MSG, cause);
    }

    //----------------------------------------------------
}
