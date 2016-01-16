/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.entity;

import blog.system.annotation.Bind;
import blog.system.loader.Load;
import blog.validation.annotation.NotEmpty;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.Pattern;
import nl.captcha.Captcha;

/**
 *
 * @author petroff
 */
public class Comment {

	private static String errorMessage = "";

	private static String type = "comment";

	@Bind
	private int id;

	private int user_id;

	private boolean enable = true;

	private String ut;

	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
			+ "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
			+ "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
			message = "comment_email_wrong")
	private String email;

	@Bind
	private int article_id;

	@Bind
	@NotEmpty
	private String comment;

	public Comment() {
		Comment.errorMessage = "";
	}

	public static boolean validate(Object object, Validator validator) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
		Captcha captcha = (Captcha) Load.session.getSession().getAttribute(Captcha.NAME);
		String answer = Load.request.getParameter("captcha");
		if (!captcha.isCorrect(answer)) {
			errorMessage = Load.bundle.getString("comment_error_captcha");
			return false;
		} else {
			//Load.session.getSession().removeAttribute(Captcha.NAME);
		}
		if (!Load.auth.isAuth() && Load.request.getParameter("email").isEmpty()) {
			errorMessage = Load.bundle.getString("comment_error_not_auth");
			return false;
		}
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			for (ConstraintViolation<Object> cv : constraintViolations) {
				errorMessage = errorMessage + String.format(
						Load.bundle.getString("main_error"),
						cv.getPropertyPath(), cv.getInvalidValue(), Load.bundle.getString(cv.getMessage()));
			}
			return false;
		}
	}

	public static boolean validate2(Object object, Validator validator) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

		if (!Load.auth.isAuth()) {
			errorMessage = Load.bundle.getString("comment_error_not_auth");
			return false;
		}
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			for (ConstraintViolation<Object> cv : constraintViolations) {
				errorMessage = errorMessage + String.format(
						Load.bundle.getString("main_error"),
						cv.getPropertyPath(), cv.getInvalidValue(), Load.bundle.getString(cv.getMessage()));
			}
			return false;
		}
	}

	public static String getErrorMessage() {
		return errorMessage;
	}

	public static void setErrorMessage(String errorMessage) {
		Comment.errorMessage = errorMessage;
	}

	public static String getType() {
		return type;
	}

	public static void setType(String type) {
		Comment.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getUt() {
		return ut;
	}

	public void setUt(String ut) {
		this.ut = ut;
	}

	public int getArticleId() {
		return article_id;
	}

	public void setArticleId(int article_id) {
		this.article_id = article_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
