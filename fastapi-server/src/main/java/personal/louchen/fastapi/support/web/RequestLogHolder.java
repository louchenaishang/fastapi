package personal.louchen.fastapi.support.web;

import personal.louchen.fastapi.entities.sys.RequestLogEntity;

/**
 * Created by louchen on 16/8/26.
 */
public class RequestLogHolder {

    private static final ThreadLocal<RequestLogEntity> REQUEST_LOG_ENTITY_THREAD_LOCAL = new InheritableThreadLocal<>();

    public RequestLogHolder() {
        super();
    }

    public static RequestLogEntity get() {
        return REQUEST_LOG_ENTITY_THREAD_LOCAL.get();
    }

    public static void set(final RequestLogEntity requestLogEntity) {
        REQUEST_LOG_ENTITY_THREAD_LOCAL.set(requestLogEntity);
    }

}
