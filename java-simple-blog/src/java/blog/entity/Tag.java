/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.entity;

import blog.system.loader.Load;
import java.util.HashMap;
import java.util.Set;
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

	private int article_link_id;

	private int user_id;

	private HashMap<String, String> translate;

	public Tag() {
		Tag.errorMessage = "";
		this.translate = new HashMap();
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

	public int getArticle_link_id() {
		return article_link_id;
	}

	public void setArticle_link_id(int article_link_id) {
		this.article_link_id = article_link_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public HashMap<String, String> getTranslate() {
		return translate;
	}

	public void setTranslate(HashMap<String, String> translate) {
		this.translate = translate;
	}

}
