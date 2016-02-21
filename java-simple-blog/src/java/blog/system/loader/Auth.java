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

    public boolean isAdmin() {
        HttpSession session = Load.request.getSession();
        if (session == null) {
            return false;
        } else {
            Integer g_id = (Integer) session.getAttribute("user_id");
            if (g_id == null) {
                return false;
            } else if (g_id == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    public String getUserName() {
        return (String) Load.request.getSession().getAttribute("user_name");
    }

    public Integer getUserId() {
        HttpSession session = Load.request.getSession();
        if (session == null) {
            return null;
        } else {
            Integer user_id = (Integer) session.getAttribute("user_id");
            return user_id;
        }
    }
}
