package personal.louchen.fastapi.excerptions;

/**
 * @author Eric.Lou
 */
public class WrongMethodException extends BaseException {
    private static final long serialVersionUID = 696040308812493525L;

    public WrongMethodException(final String resourceName, final String method) {
        super("资源" + resourceName + "不支持此方法：" + method);
    }
}
