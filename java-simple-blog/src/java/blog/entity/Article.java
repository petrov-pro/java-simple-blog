/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.entity;

import blog.system.annotation.Bind;
import blog.system.loader.Load;
import blog.validation.annotation.Internatinolaization;
import blog.validation.annotation.Unique;
import java.util.HashMap;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author petroff
 */
public class Article {

    private int id;

    private String title_id;

    private String body_id;

    @Bind
    private Boolean enable;

    @Bind
    private int weight;

    private int user_id;
    private String ut;

    @Bind
    @Unique(model_name = "Article")
    private String alias;

    @NotNull
    @Internatinolaization
    public HashMap<String, String> translate_title;

    @NotNull
    @Internatinolaization
    public HashMap<String, String> translate_body;

    private static String errorMessage = "";

    public Article() {
        Article.errorMessage = "";
        this.translate_body = new HashMap();
        this.translate_title = new HashMap();
    }

    public static String getErrorMessage() {
        return errorMessage;
    }

    public static void setErrorMessage(String errorMessage) {
        Article.errorMessage = errorMessage;
    }

    public int getId() {
        return id;
    }

    public HashMap<String, String> getTranslate_title() {
        return translate_title;
    }

    public void setTranslate_title(HashMap<String, String> translate_title) {
        this.translate_title = translate_title;
    }

    public HashMap<String, String> getTranslate_body() {
        return translate_body;
    }

    public void setTranslate_body(HashMap<String, String> translate_body) {
        this.translate_body = translate_body;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle_id() {
        return title_id;
    }

    public void setTitle_id(String title_id) {
        this.title_id = title_id;
    }

    public String getBody_id() {
        return body_id;
    }

    public void setBody_id(String body_id) {
        this.body_id = body_id;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getUt() {
        return ut;
    }

    public void setUt(String ut) {
        this.ut = ut;
    }

    public static boolean validate(Object object, Validator validator) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

        if (constraintViolations.isEmpty()) {
            return true;
        } else {
            for (ConstraintViolation<Object> cv : constraintViolations) {
                errorMessage = errorMessage + String.format(
                        Load.bundle.getString("user_registration_error"),
                        cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage());
            }
            return false;
        }
    }

}
