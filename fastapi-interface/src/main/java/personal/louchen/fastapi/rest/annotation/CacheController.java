package personal.louchen.fastapi.rest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheController {
	/**
	 * 应用于 登录&未登录;
	 * 返回对象一致
	 */
	public static final int ALL = 0;
	/**
	 * 仅应用于登录
	 */
	public static final int LOGGED = 1;
	/**
	 * 仅应用于未登录
	 */
	public static final int NOT_LOGGED = 2;
	/**
	 * 应用于 登录&未登录;
	 * 返回对象不一致
	 */
	public static final int ALL_DIFF = 3;
	
	int namespaceDepth() default 0;
	
	/**
	 * m,s,h,d作为单位
	 * @return
	 */
	String validPeriod();
	
	int scope();
	
	/**
	 * 缓存后的处理,通常用于含UserSession的处理
	 * @return
	 */
	String afterProcessClass() default "";
}
