/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.loader;

/**
 *
 * @author petroff
 */
public class Lang {

    public String get() {
        String lang = Load.session.get("lang");
        if (lang == null || lang.isEmpty()) {
            return Load.config.defaultLang;
        } else {
            return lang;
        }
    }

}
