package personal.louchen.fastapi.excerptions;

import java.util.concurrent.TimeUnit;

/**
 * @author Eric.Lou
 */
public class TooManyRequestsException extends BaseException {

    public static final String TIME_UNIT_PLACEHOLDER = "{TIME_UNIT_PLACEHOLDER}";
    public static final String LIMIT_TIMES_PLACEHOLDER = "{LIMIT_TIMES_PLACEHOLDER}";
    public static final String DEFAULT_MSG = "在一" + TIME_UNIT_PLACEHOLDER + "时间内请求次数不允许超过" + LIMIT_TIMES_PLACEHOLDER
            + "次。";
    public static final String DEFAULT_LIMIT_TIMES_STR = "限定";
    public static final String DEFAULT_TIME_UNIT_STR = "段";

    private static final long serialVersionUID = 8709388432888732021L;

    private final TimeUnit timeUnit;
    private final Integer limitTimes;

    public TooManyRequestsException() {
        super(buildDefaultMsg(DEFAULT_TIME_UNIT_STR, DEFAULT_LIMIT_TIMES_STR));
        timeUnit = null;
        limitTimes = null;
    }

    public TooManyRequestsException(final TimeUnit timeUnit, final Integer limitTimes) {
        this(buildDefaultMsg(timeUnit, limitTimes), timeUnit, limitTimes);
    }

    public TooManyRequestsException(final String message, final TimeUnit timeUnit, final Integer limitTimes) {
        super(message, NEXT_STEP_IDS_OF_RETRY_OR_REPORT_BUG);
        this.limitTimes = limitTimes;
        this.timeUnit = timeUnit;
    }

    public TooManyRequestsException(final Throwable cause, final TimeUnit timeUnit, final Integer limitTimes) {
        this(buildDefaultMsg(timeUnit, limitTimes), cause, timeUnit, limitTimes);
    }

    public TooManyRequestsException(final String message, final Throwable cause, final TimeUnit timeUnit,
                                    final Integer limitTimes) {
        super(message, cause, NEXT_STEP_IDS_OF_RETRY_OR_REPORT_BUG);
        this.limitTimes = limitTimes;
        this.timeUnit = timeUnit;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public Integer getLimitTimes() {
        return limitTimes;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + hashCode() + ":{" +
                "limitTimes:" + limitTimes +
                ", timeUnit:" + timeUnit +
                '}';
    }

    public static String buildDefaultMsg(final TimeUnit timeUnit, final Integer limitTimes) {
        return buildDefaultMsg(timeUnit.toString(),
                limitTimes == null ? DEFAULT_LIMIT_TIMES_STR : String.valueOf(limitTimes));
    }

    public static String buildDefaultMsg(final String timeUnitStr, final String limitTimesStr) {
        return DEFAULT_MSG.replace(TIME_UNIT_PLACEHOLDER, timeUnitStr).replace(LIMIT_TIMES_PLACEHOLDER, limitTimesStr);
    }
}
