/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.entity;

import blog.system.annotation.Bind;
import blog.system.loader.Load;
import blog.validation.annotation.Internatinolaization;
import blog.validation.annotation.NotEmpty;
import java.util.HashMap;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author petroff
 */
public class Comment {

    private static String errorMessage = "";

    private static String type = "comment";

    private int id;

    private int user_id;

    private boolean enable;

    private String ut;

    @Bind
    @NotEmpty
    private int article_id;

    @Internatinolaization
    private HashMap<String, String> translate;

    public Comment() {
        Comment.errorMessage = "";
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

    public HashMap<String, String> getTranslate() {
        return translate;
    }

    public void setTranslate(HashMap<String, String> translate) {
        this.translate = translate;
    }

}
