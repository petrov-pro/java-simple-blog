/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.loader;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author petroff
 */
public class Bundle {

    public ResourceBundle getBundle(HttpServletRequest r) {
        HttpServletRequest request = r;
        HttpSession s = r.getSession();
        if (s != null && s.getAttribute("lang") != null) {
            String lang = (String) s.getAttribute("lang");
            Locale locale = new Locale(lang.toLowerCase(), lang.toUpperCase());
            ResourceBundle b = ResourceBundle.getBundle("blog.messages.messages", locale);
            return b;
        } else {
            Locale locale = request.getLocale();
            return ResourceBundle.getBundle("blog.messages.messages", locale);
        }
    }

}
