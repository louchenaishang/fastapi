package personal.louchen.fastapi.excerptions;

import java.util.Arrays;

/**
 * 检测到参数错误时应抛出此异常。
 *
 * @author Eric.Lou
 */
public class WrongArgException extends BaseException {

    private static final long serialVersionUID = -5954465849908546225L;

    private final NameValuePairUtil[] args;

    //TODO:以后可以考虑开‘期望值’字段。

    //----------------------------------------------------

    public WrongArgException(final String argName, final Object actualValue) {
        super(replaceParams(argName, actualValue), NEXT_STEP_IDS_OF_RETRY);
        args = new NameValuePairUtil[]{new NameValuePairUtil(argName, actualValue)};
    }

    public WrongArgException(final String message, final String argName, final Object actualValue) {
        super(message, NEXT_STEP_IDS_OF_RETRY);
        args = new NameValuePairUtil[]{new NameValuePairUtil(argName, actualValue)};
    }

    public WrongArgException(final Throwable cause, final String argName, final Object actualValue) {
        this(replaceParams(argName, actualValue), cause, argName, actualValue);
    }

    public WrongArgException(final String message, final Throwable cause, final String argName,
                                final Object actualValue) {
        super(message, cause, NEXT_STEP_IDS_OF_RETRY);
        args = new NameValuePairUtil[]{new NameValuePairUtil(argName, actualValue)};
    }

    public WrongArgException(final NameValuePairUtil... args) {
        this(replaceParams(args), args);
    }

    public WrongArgException(final String message, final NameValuePairUtil... args) {
        super(message, NEXT_STEP_IDS_OF_RETRY);
        this.args = args;
    }

    public WrongArgException(final String message, final Throwable cause, final NameValuePairUtil... args) {
        super(message, cause, NEXT_STEP_IDS_OF_RETRY);
        this.args = args;
    }
    //----------------------------------------------------

    public NameValuePairUtil[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + hashCode() + ":{" +
                   "args:'" + Arrays.toString(args) + "'}";
    }

    protected static String replaceParams(final NameValuePairUtil... args) {
        final StringBuilder sb = new StringBuilder();
        for(final NameValuePairUtil arg : args) {
            final String argName = arg.getName();
            final Object actualValue = arg.getValue();
            sb.append("参数“").append(argName).append("”值“").append(actualValue).append("”有误，");
        }
        return sb.append("请修正后重新请求。").toString();
    }

    protected static String replaceParams(final String argName, final Object actualValue) {
        return "参数“" + argName + "”值“" + actualValue + "”有误，" + "请修正后重新请求。";
    }
}
