/**
 * Copyright (C), 上海布鲁爱电子商务有限公司
 */
package personal.louchen.fastapi.excerptions;

/**
 * @author Eric.Lou
 * @version $Id: BusinessLogicNotExpectedException.java, v 0.1 2016-05-11 下午6:16
 */
public class BusinessLogicNotExpectedException extends BaseException {
    public static final String DEFAULT_MSG = "业务逻辑不符合预期";

    public BusinessLogicNotExpectedException() {
        super(DEFAULT_MSG);
    }

    public BusinessLogicNotExpectedException(final String message) {
        super(message);
    }

    public BusinessLogicNotExpectedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BusinessLogicNotExpectedException(final Throwable cause) {
        super(DEFAULT_MSG, cause);
    }

}
