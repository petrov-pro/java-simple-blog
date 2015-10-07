/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

import blog.dao.impl.UserImpl;
import blog.entity.User;
import blog.system.dao.DaoFactory;
import blog.system.loader.Load;
import blog.system.model.Model;
import java.security.Principal;
import java.util.ResourceBundle;
import javax.servlet.http.HttpSession;

/**
 *
 * @author petroff
 */
//test
public class AuthModel extends Model {

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public Object getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getNavigator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setSession() {
        Principal principal = Load.request.getUserPrincipal();
        if (principal != null) {
            String user_name = principal.getName();
            if (user_name != null) {
                HttpSession session = Load.request.getSession();
                Integer user_id = (Integer) session.getAttribute("user_id");
                if (user_id == null || user_id == 0) {
                    //get user_id
                    UserImpl u = (UserImpl) DaoFactory.getDao("UserImpl");
                    User user = u.findByUserName(user_name);
                    session.setAttribute("user_id", user.getId());

                    session.setAttribute("user_name", user.getUser_name());
                }
            }
        }
    }

}
