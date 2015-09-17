/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.validation.annotation;

import blog.validation.ConfirmValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author petroff
 */
//Linking the validator I had shown above.
@Constraint(validatedBy = {ConfirmValidator.class})
//This constraint annotation can be used only on fields and method parameters.
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Confirm {

    String pass() default "";

    String confirm() default "";

    //The message to return when the instance of MyConfirm fails the validation.

    String message() default "Invalid Confirm";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
