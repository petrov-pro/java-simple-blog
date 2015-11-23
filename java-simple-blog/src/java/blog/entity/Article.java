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
import blog.validation.annotation.Unique;
import java.util.HashMap;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author petroff
 */
public class Article {

    private int id;

    @Bind
    private boolean enable;

    @Bind
    private int weight;

    private int user_id;
    private String ut;

    @Bind
    private String tagsStr;

    @Bind
    private int category_id;

    @Bind
    @NotEmpty
    @Unique(model_name = "Article")
    private String alias;

    private String userName;

    @Internatinolaization
    public HashMap<String, String> translate_title;

    @Internatinolaization
    public HashMap<String, String> translate_body;

    private static String errorMessage = "";
    private static String type = "article";

    public Article() {
        Article.errorMessage = "";
        this.translate_body = new HashMap();
        this.translate_title = new HashMap();
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTagsStr() {
        return tagsStr;
    }

    public void setTagsStr(String tagsStr) {
        this.tagsStr = tagsStr;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
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

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        Article.type = type;
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

}
