package io.ubun.personpm.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import io.ubun.personpm.entities.Project;

public class DateValidation implements ConstraintValidator<DefaultDateAnnotation, Project> {

	@Override
	public boolean isValid(Project value, ConstraintValidatorContext context) {
		
		return false;
	}


}
