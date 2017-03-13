package personal.louchen.fastapi.rest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by louchen on 16/8/25.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RestAttribute {
	
	String name();
	
	int len();
	
	boolean notnull();
	
	String remark();
	
}
