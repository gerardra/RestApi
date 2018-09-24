package au.com.restapi.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface JsonElement {
	
	String name() default "";
	
	String value() default "";
	
	JsonDataType dataType() default JsonDataType.STRING;

}
