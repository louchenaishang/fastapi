package personal.louchen.fastapi.rest;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import personal.louchen.fastapi.rest.annotation.*;

/**
 * Created by louchen on 16/8/25.
 */
public class RestResourceUtil {

    private static final Set<String> ignoreProperties = new HashSet<String>();

    public static List<RestDescriptor> restDescriptorList = new ArrayList<RestDescriptor>();

    public static void addRestDescriptorByClass(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        RequestMapping classRem = clazz.getAnnotation(RequestMapping.class);
        addRestDescriptor(methods, classRem);
    }

    static {
        ignoreProperties.add("class");
    }

    private static void addRestDescriptor(Method[] methods,
                                          RequestMapping classRem) {
        for (Method method : methods) {
            RequestMapping rem = method.getAnnotation(RequestMapping.class);
            if (rem != null) {
                RestDescriptor restDescriptor = new RestDescriptor();
                RequestMethod[] requestMethods = rem.method();// requestMapping
                // 上的方法定义
                if (requestMethods!=null) {
                    StringBuilder methodStr = new StringBuilder();
                    for (RequestMethod requestMethod : requestMethods) {
                        methodStr.append(requestMethod.toString() + "/");
                    }
                    String result = doSubString(methodStr, "/");
                    restDescriptor.setMethod(result);
                }
                // 路径处理
                List<String> pathStrList = new ArrayList<String>();
                String[] classRemPaths = classRem.value();// 类名上的 requestMapping
                // 上的方法定义
                String[] remPaths = rem.value();// 方法上的 requestMapping 上的方法定义
                doPathInfo(pathStrList, classRemPaths, remPaths);
                StringBuilder pathBuilder = new StringBuilder();
                for (String pathStr : pathStrList) {
                    pathBuilder.append(pathStr + "||");
                }
                String pathString = doSubString(pathBuilder, "||");
                restDescriptor.setPath(pathString);

                // 请求类型处理
                String[] classRemConsumes = classRem.consumes();// 类名上的 //
                // consumes信息
                String[] remConsumes = rem.consumes();// 方法上的 consumes信息

                String consumesString = doConsumesAndProducesInfo(
                        classRemConsumes, remConsumes);
                restDescriptor.setConsumeMediaTypes(consumesString);

                // 返回类型处理
                String[] classRemProduces = classRem.produces();// 类名上的
                // consumes信息
                String[] remProduces = rem.produces();// 方法上的 consumes信息

                String producesString = doConsumesAndProducesInfo(
                        classRemProduces, remProduces);
                restDescriptor.setProduceMediaTypes(producesString);

                restDescriptor
                        .setQueryParamDescriptors(new ArrayList<QueryParamDescriptor>());
                restDescriptor
                        .setPathParamDescriptors(new ArrayList<PathParamDescriptor>());
                Annotation[][] parameterAnnotations = method
                        .getParameterAnnotations();
                for (Annotation[] annotations : parameterAnnotations) {
                    for (Annotation annotation : annotations) {
                        if (annotation instanceof PathVariable) {
                            PathVariable pv = (PathVariable) annotation;
                            PathParamDescriptor pathParamDescriptor = new PathParamDescriptor();
                            pathParamDescriptor.setPathName(pv.value());
                            restDescriptor.getPathParamDescriptors().add(
                                    pathParamDescriptor);
                        } else if (annotation instanceof RequestParam) {
                            RequestParam rp = (RequestParam) annotation;
                            QueryParamDescriptor queryParamDescriptor = new QueryParamDescriptor();
                            queryParamDescriptor.setParamName(rp.value());
                            restDescriptor.getQueryParamDescriptors().add(
                                    queryParamDescriptor);
                        } else if (annotation instanceof RequestBody) {
                            getMethodType(method, restDescriptor);
                        }
                    }
                }
                if (parameterAnnotations == null
                        || parameterAnnotations.length < 1
                        || parameterAnnotations[0] == null
                        || parameterAnnotations[0].length < 1) {
                    getMethodType(method, restDescriptor);
                }

                //返回类型
                //restDescriptor.setProduceMeta(method.getReturnType().getName());
                RestResult rr = method.getAnnotation(RestResult.class);
                restDescriptor.setProduceMeta(rr.className());

                RestParams restParams = method.getAnnotation(RestParams.class);
                if (restParams != null) {
                    RestParam[] items = restParams.value();
                    if (items != null) {
                        for (RestParam restParam : items) {
                            for (PathParamDescriptor pathParamDescriptor : restDescriptor
                                    .getPathParamDescriptors()) {
                                if (pathParamDescriptor.getPathName().equals(
                                        restParam.name())) {
                                    pathParamDescriptor.setRemark(restParam
                                            .remark());
                                    break;
                                }
                            }
                            for (QueryParamDescriptor queryParamDescriptor : restDescriptor
                                    .getQueryParamDescriptors()) {
                                if (queryParamDescriptor.getParamName().equals(
                                        restParam.name())) {
                                    queryParamDescriptor.setRemark(restParam
                                            .remark());
                                    break;
                                }
                            }
                        }
                    }
                }
                // rest description
                RestDescription restDescription = method
                        .getAnnotation(RestDescription.class);
                if (restDescription != null) {
                    restDescriptor.setDescription(restDescription.value());
                }
                // cache description
                CacheController cacheControllerDescription = method
                        .getAnnotation(CacheController.class);
                if (cacheControllerDescription != null) {
                    restDescriptor.setCacheController("NamespaceDepth:"
                            + cacheControllerDescription.namespaceDepth()
                            + ",ValidPeriod:"
                            + cacheControllerDescription.validPeriod());
                }

                restDescriptorList.add(restDescriptor);

            }
        }
    }

    private static void getMethodType(Method method, RestDescriptor restDescriptor) {
        Type[] types = method.getParameterTypes();
        for (Type type : types) {
            if (StringUtils.hasText(restDescriptor.getConsumeMeta())) {
                restDescriptor.setConsumeMeta(restDescriptor.getConsumeMeta()
                        + ","
                        + type.toString().replaceAll("\\<", "&lt;")
                        .replaceAll("\\>", "&gt;"));
            } else {
                restDescriptor.setConsumeMeta(type.toString()
                        .replaceAll("\\<", "&lt;").replaceAll("\\>", "&gt;"));
            }
        }

    }

    public static String packages() throws Exception {

        StringBuffer sb = new StringBuffer();
        sb.append("<html><head> <meta http-equiv='content-type' content='text/html; charset=UTF-8' /> </head> <body>");
        sb.append("<style type='text/css'>");
        sb.append("body{margin:0px;padding:0px;font-size: 10;}div{width:100%;overflow:auto;height:500px;}div ul{width:100%;float:left;list-style:none;list-style-type:none;margin:0px;padding:0px;}");
        sb.append("div ul li{height:80px;float:left;word-break:break-all;border:1px #666666 solid;list-style:none;list-style-type:none;}");
        sb.append(".title1{width:15%;height:80px;line-height:80px;text-align:center;}");
        sb.append(".title2{width:210px;height:80px;line-height:80px;text-align:center;}");
        sb.append(".title3{width:15%;height:80px;line-height:80px;text-align:center;}");
        sb.append(".title4{width:15%;height:80px;line-height:80px;text-align:center;}");
        sb.append(".title5{width:20%;height:80px;line-height:80px;text-align:center;}");
        sb.append(".title6{width:10%;height:80px;line-height:80px;text-align:center;}");
        sb.append(".content1{width:15%;}");
        sb.append(".content2{width:210px;}");
        sb.append(".content3{width:15%;overflow:auto;}");
        sb.append(".content4{width:15%;overflow:auto;}");
        sb.append(".content5{width:20%;overflow:auto;}");
        sb.append(".content6{width:10%;overflow:auto;}");
        sb.append("</style>");

        sb.append("<div style='overflow-x: auto; overflow-y: auto; margin:10px; height: 500px; width:98%;'>");
        sb.append("<ul><li class='title1'>PATH</li><li class='title2'>MediaType</li><li class='title3'>参数</li><li class='title4'>描述信息</li><li class='title5'>返回</li><li class='title6'>缓存</li></ul>");
        for (RestDescriptor restDescriptor : restDescriptorList) {
            sb.append("<ul>");
            sb.append("<li class='content1'>" + restDescriptor.getMethod()
                    + " " + restDescriptor.getPath() + "</li>");
            sb.append("<li class='content2'>Consume:"
                    + restDescriptor.getConsumeMediaTypes() + "<br>Produce:"
                    + restDescriptor.getProduceMediaTypes() + "</li>");
            sb.append("<li class='content3'>");
            for (PathParamDescriptor pathParamDescriptor : restDescriptor
                    .getPathParamDescriptors()) {
                sb.append("Path&lt;" + pathParamDescriptor.getPathName()
                        + "&gt;:" + nvl(pathParamDescriptor.getRemark())
                        + "<br>");
            }
            for (QueryParamDescriptor queryParamDescriptor : restDescriptor
                    .getQueryParamDescriptors()) {
                sb.append("Query&lt;" + queryParamDescriptor.getParamName()
                        + "&gt;:" + nvl(queryParamDescriptor.getRemark())
                        + "<br>");
            }
            sb.append("</li>");
            sb.append("<li class='content4'> "
                    + nvl(restDescriptor.getDescription()) + "</li>");
            sb.append("<li class='content5'>返回："
                    + nvl(restDescriptor.getProduceMeta()) + "<br>输入:"
                    + nvl(restDescriptor.getConsumeMeta()) + "</li>");
            sb.append("<li class='content6'> "
                    + nvl(restDescriptor.getCacheController()) + "</li>");
            sb.append("</ul>");
        }
        // class info view
        sb.append("</div>");
        sb.append("\n<br><table><tr><td>class:</td><td><input id='className' size=45 type='text'/><input type='button' value='查看' onclick=\"document.getElementById('classIframe').src='/api/class?className='+document.getElementById('className').value;\"/> </td><td><IFRAME height='150px' width='700px' id='classIframe'></IFRAME></td></tr></table>");

        sb.append("</body></html>");

        return sb.toString();
    }

    @SuppressWarnings("rawtypes")
    public static String getClassInfo(Class cls) throws Exception {
        PropertyUtilsBean pub = new PropertyUtilsBean();
        PropertyDescriptor[] pds = pub.getPropertyDescriptors(cls);
        StringBuffer sb = new StringBuffer();
        sb.append("<html><head><meta http-equiv='content-type' content='text/html; charset=UTF-8' /></head><body>");
        sb.append("<table border=1><tr><td>属性</td><td>类型</td><td>名称</td><td>长度</td><td>Not Null</td><td>描述</td></tr>");
        for (PropertyDescriptor propertyDescriptor : pds) {
            if (ignoreProperties.contains(propertyDescriptor.getName()))
                continue;
            sb.append("<tr><td>");
            sb.append(propertyDescriptor.getName() + "</td><td>"
                    + propertyDescriptor.getPropertyType().getName() + "</td>");
            Field field = propertyDescriptor.getReadMethod()
                    .getDeclaringClass()
                    .getDeclaredField(propertyDescriptor.getName());
            if (field != null) {
                RestAttribute restAttribute = field
                        .getAnnotation(RestAttribute.class);
                if (restAttribute != null) {
                    sb.append("<td>" + restAttribute.name() + "</td>" + "<td>"
                            + restAttribute.len() + "</td>" + "<td>"
                            + restAttribute.notnull() + "</td>" + "<td>"
                            + restAttribute.remark() + "</td>");
                }
            } else {
                sb.append("<td colspan='3'/>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    public static  List<RestClassWrapper> getRestClassWrapper(Class cls) throws Exception {
        PropertyUtilsBean pub = new PropertyUtilsBean();
        PropertyDescriptor[] pds = pub.getPropertyDescriptors(cls);
        List<RestClassWrapper> list = new ArrayList<>();
        for (PropertyDescriptor propertyDescriptor : pds) {
            if (ignoreProperties.contains(propertyDescriptor.getName()))
                continue;
            RestClassWrapper wrapper = new RestClassWrapper();
            wrapper.setClassName(propertyDescriptor.getName());
            wrapper.setClassType(propertyDescriptor.getPropertyType().getName());
            Field field = propertyDescriptor.getReadMethod()
                    .getDeclaringClass()
                    .getDeclaredField(propertyDescriptor.getName());
            if (field != null) {
                RestAttribute restAttribute = field
                        .getAnnotation(RestAttribute.class);
                if (restAttribute != null) {
                    wrapper.setName(restAttribute.name());
                    wrapper.setLen(restAttribute.len());
                    wrapper.setNotnull(restAttribute.notnull());
                    wrapper.setRemark(restAttribute.remark());
                }
            }
            list.add(wrapper);
        }
        return list;
    }

    private static String nvl(Object obj) {
        if (obj == null)
            return "";
        return obj.toString();
    }

    /**
     * 截取以'/'为结尾的字符串
     *
     * @return
     */
    private static String doSubString(StringBuilder stringBuilder, String suffix) {
        String newValue = stringBuilder.toString();
        if (newValue.endsWith(suffix)) {
            newValue = newValue.substring(0, newValue.lastIndexOf(suffix));
        }
        return newValue;
    }

    /**
     * 处理路径信息
     *
     * @param resultList
     * @param classRemPaths
     * @param remPaths
     */
    private static void doPathInfo(List<String> resultList,
                                   String[] classRemPaths, String[] remPaths) {
        for (int i = 0; i < classRemPaths.length; i++) {
            String base = classRemPaths[i];
            for (int j = 0; j < remPaths.length; j++) {
                String appendStr = remPaths[i];
                resultList.add(base + appendStr);
            }

        }
    }

    /**
     * 处理接收和返回的类型信息
     *
     * @param classRem
     * @param rem
     * @return
     */
    private static String doConsumesAndProducesInfo(String[] classRem,
                                                    String[] rem) {
        StringBuilder result = new StringBuilder();
        if (rem!=null) {
            for (String remStr : rem) {
                result.append(remStr + "||");
            }
        } else {
            for (String remStr : classRem) {
                result.append(remStr + "||");
            }
        }
        return doSubString(result, "||");
    }
}
