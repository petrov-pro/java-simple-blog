/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.validation;

import blog.validation.annotation.NotEmpty;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author petroff
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {

	private String errorMessagename;

	@Override
	public void initialize(NotEmpty constraintAnnotation) {
		errorMessagename = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String field, ConstraintValidatorContext context) {
		if (field.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
