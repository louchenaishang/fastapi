package personal.louchen.fastapi.entities.sys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by louchen on 16/8/26.
 */
@Entity
@Table(name = "ai_api_request_log")
public class RequestLogEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @Version
    private long version;

    @Column(name = "REQUEST_IP", nullable = true)
    private String requestIp;
    @Column(name = "METHOD", nullable = false)
    private String method;
    @Column(name = "URI", nullable = false)
    private String uri;
    @Lob
    @Column(name = "QUERY_STRING", nullable = true)
    private String queryString;
    @Lob
    @Column(name = "REQUEST_BODY", nullable = true)
    private String requestBody;
    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;
    @Lob
    @Column(name = "RESULT", nullable = true)
    private String result;
    @Column(name = "SUCCESS", nullable = false)
    private boolean success;
    @Column(name = "ELAPSED_MILLISECONDS", nullable = false)
    private long elapsedMilliseconds;
    @Column(name = "ERROR_CODE", nullable = true)
    private String errorCode;
    @Lob
    @Column(name = "ERROR_DESC", nullable = true)
    private String errorDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getElapsedMilliseconds() {
        return elapsedMilliseconds;
    }

    public void setElapsedMilliseconds(long elapsedMilliseconds) {
        this.elapsedMilliseconds = elapsedMilliseconds;
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

}
