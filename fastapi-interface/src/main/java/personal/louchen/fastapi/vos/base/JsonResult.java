package personal.louchen.fastapi.vos.base;


/**
 * @author: Eric.Lou
 */
public class JsonResult<T> {

    public JsonResult() {
    }

    public JsonResult(T data) {
        this.data = data;
        this.success = true;
    }

    public JsonResult(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.success = false;
    }

    public JsonResult(String errorCode, String errorDesc, long elapsedMilliseconds) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.success = false;
        this.elapsedMilliseconds = elapsedMilliseconds;
    }

    /**
     * 是否处理成功
     */
    private boolean success;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 错误代码
     */
    private String errorCode;

    /**
     * 错误描述
     */
    private String errorDesc;

    /**
     * 处理耗时(毫秒)
     */
    private long elapsedMilliseconds;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public long getElapsedMilliseconds() {
        return elapsedMilliseconds;
    }

    public void setElapsedMilliseconds(long elapsedMilliseconds) {
        this.elapsedMilliseconds = elapsedMilliseconds;
    }

}
