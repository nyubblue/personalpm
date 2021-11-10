package io.ubun.personpm.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidation.class)
public @interface DefaultDateAnnotation {
	public String value() default "yyyy/MM/dd";

	   //error message  
    public String message() default "must contain jtp";  
//represents group of constraints     
    public Class<?>[] groups() default {};  
//represents additional information about annotation  
    public Class<? extends Payload>[] payload() default {};  
}
