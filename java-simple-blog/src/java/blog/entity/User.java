/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.entity;

import blog.system.annotation.Bind;
import blog.system.loader.Load;
import blog.system.tools.Md5;
import blog.validation.annotation.Confirm;
import blog.validation.annotation.NotEmpty;
import blog.validation.annotation.Unique;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author petroff
 */
@Confirm(pass = "password", confirm = "confirm")
public class User {

    private static String errorMessage;

    public int id;

    @Bind
    @NotEmpty
    public String email;

    @Bind
    @NotEmpty
    @Unique(model_name = "User")
    public String user_name;

    @Bind
    @NotEmpty
    public String password;

    @Bind
    @NotEmpty

    public String confirm;

    public User() {
        errorMessage = "";
    }

    public static String getErrorMessage() {
        return errorMessage;
    }

    public static void setErrorMessage(String errorMessage) {
        User.errorMessage = errorMessage;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPasswordHash() {
        String md5Hash = Md5.md5Custom(password);
        return md5Hash;
    }

}
