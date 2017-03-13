package personal.louchen.fastapi.utils;

import com.alibaba.fastjson.JSON;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by louchen on 16/8/25.
 */
public class ReflectHelpUtil {

    /**
     * 方法参数,key:value json格式
     * @param className
     * @param methodName
     * @param args
     * @return
     * @throws NotFoundException
     */
    public static String getMethodArgsJsonString(String className,String methodName,Object[] args) throws NotFoundException {
        String[] methodArgsNames = ReflectHelpUtil.getMethodArgsNames(className,methodName);
        //记录参数
        Map<String,Object> paramMap = new HashMap<>();
        if (!className.contains("com.sun.proxy.$Proxy") && !className.contains("$$EnhancerBySpringCGLIB$$")) {
            if (args != null && args.length > 0) {
                for(int i=0; i<args.length;i++){
                    paramMap.put(methodArgsNames[i],args[i]);
                }
            }else{
                return "";
            }
        }
        return JSON.toJSONString(paramMap);
    }

    /**
     * 方法参数名称
     * @param className
     * @param methodName
     * @return
     */
    public static String[] getMethodArgsNames(String className,String methodName) throws NotFoundException {
        String[] paramNames = null;
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get(className);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            paramNames = new String[0];
            return paramNames;
        }
        paramNames = new String[cm.getParameterTypes().length];
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < paramNames.length; i++) {
            paramNames[i] = attr.variableName(i + pos);
        }
        return paramNames;
    }

    /**
     * 方法参数类型
     * @param className
     * @param methodName
     * @return
     */
    public static Class[] getMethodArgsClasses(String className,String methodName) {
        Class[] paramTypes = null;
        try {
            Class clazz = Class.forName(className);
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String currentMethodName = method.getName();
                if(currentMethodName.equals(methodName)){
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    paramTypes = new Class[parameterTypes.length] ;
                    for (int i = 0; i < parameterTypes.length; i++) {
                        paramTypes[i] = Class.forName(parameterTypes[i].getName());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            //ignore
        }
        return paramTypes;
    }

}
