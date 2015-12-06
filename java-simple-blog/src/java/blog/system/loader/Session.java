/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.loader;

import javax.servlet.http.HttpSession;

/**
 *
 * @author petroff
 */
public class Session {

    public HttpSession getSession() {
        return Load.request.getSession();
    }

    public String get(String key) {
        HttpSession hs = this.getSession();
        if (hs != null) {
            return (String) hs.getAttribute(key);
        } else {
            return null;
        }
    }

    public void set(String key, String value) {
        HttpSession hs = this.getSession();
        if (hs != null) {
            hs.setAttribute(key, value);
        }
    }
}
