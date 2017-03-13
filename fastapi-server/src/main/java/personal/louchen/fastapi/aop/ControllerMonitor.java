package personal.louchen.fastapi.aop;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.DefaultThrowableRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import personal.louchen.fastapi.daos.jdao.RequestLogDao;
import personal.louchen.fastapi.entities.sys.RequestLogEntity;
import personal.louchen.fastapi.excerptions.*;
import personal.louchen.fastapi.rest.RestConst;
import personal.louchen.fastapi.services.parameter.Parameter;
import personal.louchen.fastapi.utils.ApiSecurityUtil;
import personal.louchen.fastapi.utils.DateUtil;
import personal.louchen.fastapi.vos.base.JsonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 记录参数,记录耗时,记录返回结果
 * @author Eric.Lou
 */
@Component
@Aspect
public class ControllerMonitor {

    private Logger logger = LoggerFactory.getLogger(ControllerMonitor.class);

    @Autowired
    private RequestLogDao requestLogDao;

    @Autowired
    private Parameter parameter;

    @Around("execution(* personal.louchen.fastapi.controller.*Controller.*(..))")
    public Object proxy(ProceedingJoinPoint pjp) throws Exception {
        //执行方法前
        long start = System.currentTimeMillis();

        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String fullMethodName = className + "." + methodName;

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        RequestLogEntity rle = new RequestLogEntity();
        rle.setMethod(request.getMethod());
        rle.setUri(request.getRequestURI());
        rle.setQueryString(request.getQueryString());
        rle.setCreateTime(DateUtil.getNow());
        rle.setSuccess(false);

        byte[] bytes = IOUtils.toByteArray(request.getInputStream());
        Object requestBody = JSON.parseObject(bytes, Object.class);
        if(requestBody!=null){
            rle.setRequestBody(JSON.toJSONString(requestBody));
        }

        //执行方法
        Object result = null;
        try {
            validateSign(rle);
            result = pjp.proceed();
            rle.setSuccess(true);
            if (result instanceof JsonResult) {
                ((JsonResult<?>) result).setSuccess(true);
                ((JsonResult<?>) result).setErrorCode(RestConst.Response.SUCCESS.code);
                ((JsonResult<?>) result).setErrorDesc(RestConst.Response.SUCCESS.textCN);
            }
            logger.debug(fullMethodName + "执行完毕,结果:");
            logger.debug(JSON.toJSONString(((JsonResult<?>) result).getData()));
        } catch (UnsignException e){
            result = new JsonResult<Object>(RestConst.Response.UNSIGN.code, RestConst.Response.UNSIGN.textCN);
        } catch (WrongArgException | WrongStateException e){
            result = new JsonResult<Object>(RestConst.Response.ARGS_VALIDATE_FAILED.code, e.getMessage());
            logger.debug(fullMethodName + "执行出错,详情:", e);//debug开发输出日志,线上无需记录,已记录到数据库
        } catch (UnauthorizedException e){
            result = new JsonResult<Object>(RestConst.Response.UNAUTHORIZED.code, RestConst.Response.UNAUTHORIZED.textCN);
            logger.debug(fullMethodName + "执行出错,详情:", e);//debug开发输出日志,线上无需记录,已记录到数据库
        } catch (ForbiddenException e){
            result = new JsonResult<Object>(RestConst.Response.FORBIDDEN.code, RestConst.Response.FORBIDDEN.textCN);
            logger.debug(fullMethodName + "执行出错,详情:", e);//debug开发输出日志,线上无需记录,已记录到数据库
        } catch (ObjectOptimisticLockingFailureException e){
            result = new JsonResult<Object>(RestConst.Response.DATA_VERSION_FAILED.code, RestConst.Response.DATA_VERSION_FAILED.textCN);
            logger.debug(fullMethodName + "执行出错,详情:", e);//debug开发输出日志,线上无需记录,已记录到数据库
        } catch (Throwable e) {
            String[] render = DefaultThrowableRenderer.render(e);
            StringBuilder sb = new StringBuilder("");
            for(int i=0;i<render.length;i++){
                sb.append(render[i]);
            }
            result = new JsonResult<Object>(RestConst.Response.UNKNOWN.code, sb.toString());
            logger.debug(fullMethodName + "执行出错,详情:", e);//debug开发输出日志,线上无需记录,已记录到数据库
        }

        //执行方法后
        long end = System.currentTimeMillis();
        long elapsedMilliseconds = end - start;
        rle.setElapsedMilliseconds(elapsedMilliseconds);

        if (result != null && result instanceof JsonResult) {
            ((JsonResult<?>) result).setElapsedMilliseconds(elapsedMilliseconds);

            rle.setErrorCode(((JsonResult<?>) result).getErrorCode());
            rle.setErrorDesc(((JsonResult<?>) result).getErrorDesc());
            if(((JsonResult<?>) result).getData()!=null){
                rle.setResult(JSON.toJSONString(((JsonResult<?>) result).getData()));
            }
        }else{
            rle.setResult(JSON.toJSONString(result));
        }

        //持久化到数据库
        requestLogDao.save(rle);

        return result;
    }

    /**
     * 验证签名
     * @param rle
     * @throws UnsignException
     */
    private void validateSign(RequestLogEntity rle) throws UnsignException{
        if(rle==null){
            throw new UnsignException("");
        }
        ApiSecurityUtil.validateSign(rle.getQueryString(), parameter.getApiKey());
    }

}