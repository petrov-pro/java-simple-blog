/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.tools;

import blog.system.annotation.Bind;
import blog.system.loader.Load;
import java.lang.reflect.Field;

/**
 *
 * @author petroff
 */
public class BindParams {

    public static Object bind(Object obj) {
        Class c = obj.getClass();
        Field[] publicFields = c.getFields();
        for (Field field : publicFields) {
            Class fieldType = field.getType();
            Bind bind = field.getAnnotation(Bind.class);
            if (bind != null) {
                try {
                    String param_name = field.getName();
                    String param = Load.request.getParameter(param_name);
                    if (param == null || param.isEmpty()) {
                        param = null;
                    }
                    field.set(obj, param);
                } catch (java.lang.Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
        return obj;
    }

}
