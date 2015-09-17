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
public class Auth {

    public boolean isAuth() {
        HttpSession session = Load.request.getSession();
        if (session == null) {
            return false;
        } else {
            Integer user_id = (Integer) session.getAttribute("user_id");
            if (user_id == null) {
                return false;
            } else {
                return true;
            }
        }
    }

    public String getUserName() {
        return (String) Load.request.getSession().getAttribute("user_name");
    }

    public Integer getUserId() {
        return (Integer) Load.request.getSession().getAttribute("user_id");
    }
}
