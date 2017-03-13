package personal.louchen.fastapi.excerptions;

import com.google.common.collect.Sets;

import java.util.Collection;

/**
 * @author Eric.Lou
 */
public class ForbiddenException extends BaseException {
    public static final String DEFAULT_MSG = "您没有权限访问。";
    /**
     * 下一步可以去请求授权或切换用户。
     * 注：目前还没有切换用户的功能。
     */
    public static final Collection<String> NEXT_STEP_IDS_OF_AUTHORIZE_OR_SWITCH_USER = Sets.newHashSet("requireAuthorize",
                                                                                                       "switchUser");
    private static final long serialVersionUID = -2792920730677138805L;

    //----------------------------------------------------

    public ForbiddenException() {
        super(DEFAULT_MSG, NEXT_STEP_IDS_OF_AUTHORIZE_OR_SWITCH_USER);
    }

    public ForbiddenException(final String message) {
        super(message, NEXT_STEP_IDS_OF_AUTHORIZE_OR_SWITCH_USER);
    }

    public ForbiddenException(final String message, final Throwable cause) {
        super(message, cause, NEXT_STEP_IDS_OF_AUTHORIZE_OR_SWITCH_USER);
    }

    public ForbiddenException(final Throwable cause) {
        super(DEFAULT_MSG, cause, NEXT_STEP_IDS_OF_AUTHORIZE_OR_SWITCH_USER);
    }

    //----------------------------------------------------
}
