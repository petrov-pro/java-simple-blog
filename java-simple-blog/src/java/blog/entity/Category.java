/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.entity;

import blog.system.annotation.Bind;
import blog.system.loader.Load;
import blog.validation.annotation.Internatinolaization;
import java.util.HashMap;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author petroff
 */
public class Category {

    private static String errorMessage = "";

    private int id;

    @Bind
    private boolean enable;

    @Bind
    @NotNull
    //@Unique(model_name = "Category")
    private String alias;

    @Bind
    @NotNull
    private int weight;

    private int title_id;

    @NotNull
    @Internatinolaization
    public HashMap<String, String> translate;

    public Category() {
        this.translate = new HashMap();
    }

    public static String getErrorMessage() {
        return errorMessage;
    }

    public static void setErrorMessage(String errorMessage) {
        Category.errorMessage = errorMessage;
    }

    public int getId() {
        return id;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }

    public HashMap getTranslate() {
        return translate;
    }

    public void setTranslate(HashMap translate) {
        this.translate = translate;
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
