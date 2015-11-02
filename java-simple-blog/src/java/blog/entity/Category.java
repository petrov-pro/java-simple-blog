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
import javax.validation.constraints.NotNull;

/**
 *
 * @author petroff
 */
public class Category {

    private static String errorMessage = "";

    private static String type = "category";

    private int id;

    @Bind
    private boolean enable;

    @Bind
    @Unique(model_name = "Category")
    @NotEmpty
    private String alias;

    @Bind
    @NotEmpty
    private int weight;

    @Internatinolaization
    public HashMap<String, String> translate;

    public Category() {
        Category.errorMessage = "";
        this.translate = new HashMap();
    }

    public static String getTypeS() {
        return type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        type = type;
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
                        Load.bundle.getString("main_error"),
                        cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage());
            }
            return false;
        }
    }

}
