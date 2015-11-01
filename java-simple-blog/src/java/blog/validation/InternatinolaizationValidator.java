/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.validation;

import blog.system.tools.Logger;
import blog.validation.annotation.Internatinolaization;
import java.util.Map;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author petroff
 */
public class InternatinolaizationValidator implements ConstraintValidator<Internatinolaization, Object> {

    private String errorMessage;

    @Override
    public void initialize(Internatinolaization constraintAnnotation) {
        errorMessage = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        Map<String, String> i18n = (Map) obj;
        if (i18n.size() == 0) {
            return false;
        }
        for (Map.Entry<String, String> entry : i18n.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.isEmpty() || value.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
