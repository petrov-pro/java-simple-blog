/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.entity;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author petroff
 */
public class User {

	@NotNull
	public String login;

	@NotNull
	public String password;

	public static void validate(Object object, Validator validator) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

		System.out.println(object);
		System.out.println(String.format("Кол-во ошибок: %d",
				constraintViolations.size()));

		for (ConstraintViolation<Object> cv : constraintViolations) {
			System.out.println(String.format(
					"Внимание, ошибка! property: [%s], value: [%s], message: [%s]",
					cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
		}
	}

}
