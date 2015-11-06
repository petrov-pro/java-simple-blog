/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.entity;

import blog.system.loader.Load;
import blog.validation.annotation.NotEmpty;
import java.util.HashMap;
import java.util.Set;
import static javafx.scene.transform.Transform.translate;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author petroff
 */
public class Tag {

	private static String errorMessage = "";

	private static String type = "tag";

	private int id;

	private int user_id;

	@NotEmpty
	private String name;

	public Tag() {
		Tag.errorMessage = "";
	}

	public static boolean validate(Object object, Validator validator) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			for (ConstraintViolation<Object> cv : constraintViolations) {
				errorMessage = errorMessage + String.format(
						Load.bundle.getString("main_error"),
						cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage());
			}
			return false;
		}
	}

	public static String getErrorMessage() {
		return errorMessage;
	}

	public static void setErrorMessage(String errorMessage) {
		Tag.errorMessage = errorMessage;
	}

	public static String getType() {
		return type;
	}

	public static void setType(String type) {
		Tag.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
