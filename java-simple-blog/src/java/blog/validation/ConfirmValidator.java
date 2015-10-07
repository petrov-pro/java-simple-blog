/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.validation;

import blog.validation.annotation.Confirm;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author petroff
 */
public class ConfirmValidator implements ConstraintValidator<Confirm, Object> {

    private String confirm_f;
    private String pass_f;
    private String errorMessagename;

    @Override
    public void initialize(Confirm constraintAnnotation) {
        confirm_f = constraintAnnotation.confirm();
        pass_f = constraintAnnotation.pass();
        errorMessagename = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        Class c = obj.getClass();
        String pass = "";
        String confirm = "";
        try {
            Field f = c.getField(pass_f);
            pass = (String) f.get(obj);

            f = c.getField(confirm_f);
            confirm = (String) f.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
        if (pass == null || confirm == null || !pass.equals(confirm)) {
            return false;
        } else {
            return true;
        }
    }
}
