/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.tools;

import blog.system.annotation.Bind;
import blog.system.loader.Load;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletRequest;

/**
 *
 * @author petroff
 */
public class BindParams {

    public static Object bind(Object obj) {
        Class c = obj.getClass();

        Field[] publicFields = c.getDeclaredFields();
        for (Field field : publicFields) {

            Class fieldType = field.getType();
            Bind bind = field.getAnnotation(Bind.class);
            if (bind != null) {
                try {
                    String param_name = field.getName();
                    String param_raw = Load.request.getParameter(param_name.toLowerCase(Locale.ENGLISH));
                    String param;
                    if (param_raw == null) {
                        param = "";
                    } else {
                        param = param_raw.trim();
                    }
                    field.setAccessible(true);
                    if (field.getType().equals(int.class)) {
                        try {
                            int p = Integer.parseInt(param);
                            field.setInt(obj, p);
                        } catch (NumberFormatException nfe) {
                            Logger.write(nfe.toString());
                        }
                    } else if (field.getType().equals(boolean.class)) {
                        field.setBoolean(obj, Boolean.parseBoolean(param));
                    } else {
                        field.set(obj, param);
                    }

                } catch (java.lang.Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
        return obj;
    }

    public static Map<String, String> getParameterMap(ServletRequest request, String mapName) {

        Map<String, String> result = new HashMap();

        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            if (name.startsWith(mapName + "[") && name.endsWith("]")) {
                result.put(name.substring(name.indexOf("[") + 1, name.indexOf("]")), request.getParameter(name));
            }
        }

        return result;
    }

}
