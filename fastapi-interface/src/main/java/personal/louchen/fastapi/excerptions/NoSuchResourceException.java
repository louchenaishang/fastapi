package personal.louchen.fastapi.excerptions;

/**
 * 没有获取到某资源时抛出本异常。
 *
 * @author Eric.Lou
 */
public class NoSuchResourceException extends BaseException {
    private static final long serialVersionUID = 8473513127466395625L;

    private final String resourceType;
    @SuppressWarnings("serial")
    private final Object resourceId;

    //----------------------------------------------------

    public NoSuchResourceException(final String resourceType, final Object resourceId) {
        super("没有id为“" + resourceId + "”的“" + resourceType + "”资源", NEXT_STEP_IDS_OF_REPORT_PROBLEM);
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }

    public NoSuchResourceException(final String message, final String resourceType, final Object resourceId) {
        super(message, NEXT_STEP_IDS_OF_REPORT_PROBLEM);
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }

    public NoSuchResourceException(final String message, final Throwable cause, final String resourceType,
                                      final Object resourceId) {
        super(message, cause, NEXT_STEP_IDS_OF_REPORT_PROBLEM);
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }

    //----------------------------------------------------

    public String getResourceType() {
        return resourceType;
    }

    public Object getResourceId() {
        return resourceId;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + hashCode() + ":{" +
                   "resourceType:'" + resourceType + '\'' +
                   ", resourceId:" + resourceId +
                   '}';
    }
}
