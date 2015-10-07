/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.validation.annotation;

import blog.validation.InternatinolaizationValidator;
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

@Constraint(validatedBy = {InternatinolaizationValidator.class})
@Target({ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Internatinolaization {

    String message() default "Internatinolaization field must be fill";
    
        Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
