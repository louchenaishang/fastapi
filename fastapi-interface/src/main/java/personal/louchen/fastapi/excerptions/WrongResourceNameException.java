package personal.louchen.fastapi.excerptions;

/**
 * @author Eric.Lou
 */
public class WrongResourceNameException extends BaseException {
    private static final long serialVersionUID = 8092392564212234541L;

    public WrongResourceNameException(final String resourceName) {
        super("资源名有误：" + resourceName);
    }
}
