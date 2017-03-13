package personal.louchen.fastapi.excerptions;

import java.util.Collection;

/**
 * 状态错误时应抛出此异常。比如请求改变活动状态（正式执行、中止、测试执行等）时，当前的状态不对。
 *
 * @author Eric.Lou
 */
public class WrongStateException extends BaseException {
    private static final String STATE_NAME_PLACEHOLDER = "{STATE_NAME_PLACEHOLDER}";
    private static final String ACTUAL_STATE_PLACEHOLDER = "{ACTUAL_STATE_PLACEHOLDER}";
    private static final String DEFAULT_MSG = "“" + STATE_NAME_PLACEHOLDER + "”当前状态“" + ACTUAL_STATE_PLACEHOLDER + "”不对。";

    private static final long serialVersionUID = 74075162838530489L;

    protected static String replaceParams(final String stateName, final Object actualState) {
        return replaceParams(DEFAULT_MSG, stateName, actualState);
    }

    protected static String replaceParams(final String msgTemplate, final String stateName, final Object actualState) {
        return msgTemplate.replace(STATE_NAME_PLACEHOLDER, stateName)
                          .replace(ACTUAL_STATE_PLACEHOLDER, String.valueOf(actualState));
    }

    //----------------------------------------------------

    private final String stateName;
    @SuppressWarnings("serial")
    private final Object actualState;

    //----------------------------------------------------

    public WrongStateException(final String stateName, final Object actualState) {
        super(replaceParams(stateName, actualState), NEXT_STEP_IDS_OF_RETRY);
        this.stateName = stateName;
        this.actualState = actualState;
    }

    public WrongStateException(final String message, final String stateName, final Object actualState) {
        super(message, NEXT_STEP_IDS_OF_RETRY);
        this.stateName = stateName;
        this.actualState = actualState;
    }

    public WrongStateException(final Throwable cause, final String stateName, final Object actualState) {
        this(replaceParams(stateName, actualState), cause, stateName, actualState);
    }

    public WrongStateException(final String message, final Throwable cause, final String stateName,
                                  final Object actualState) {
        super(message, cause, NEXT_STEP_IDS_OF_RETRY);
        this.stateName = stateName;
        this.actualState = actualState;
    }

    public WrongStateException(final Throwable cause, final Collection<String> nextStepIds,
                                  final String stateName, final Object actualState) {
        super(replaceParams(stateName, actualState), cause, nextStepIds);
        this.stateName = stateName;
        this.actualState = actualState;
    }

    public WrongStateException(final String message, final Throwable cause, final Collection<String> nextStepIds,
                                  final String stateName, final Object actualState) {
        super(message, cause, nextStepIds);
        this.stateName = stateName;
        this.actualState = actualState;
    }

    public WrongStateException(final String message, final Collection<String> nextStepIds, final String stateName,
                                  final Object actualState) {
        super(message, nextStepIds);
        this.stateName = stateName;
        this.actualState = actualState;
    }

    //----------------------------------------------------

    public String getStateName() {
        return stateName;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + hashCode() + ":{" +
                   "stateName:'" + stateName + '\'' +
                   ", actualState:" + actualState +
                   '}';
    }

    public Object getActualState() {
        return actualState;
    }

}
