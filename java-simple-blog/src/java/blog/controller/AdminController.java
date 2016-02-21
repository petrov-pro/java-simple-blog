/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.system.controller.ControllerImpl;
import blog.system.exception.PersistException;
import blog.system.loader.Load;
import blog.system.tools.Http;
import blog.model.AdminModel;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author petroff
 */
public class AdminController extends ControllerImpl<AdminController> {

    @Override
    public void index() throws ServletException, IOException {
        Http.redirect();
    }

    public void listuser() {
        AdminModel am = (AdminModel) Load.model.name("Admin");
        am.listUser();
        am.navigator.setViewProfile("/navigator/admin.jsp", "admin");
        Load.view.name("/admin/listuser.jsp", am);
    }

    public void userdel(String user_id) throws IOException, PersistException {
        AdminModel am = (AdminModel) Load.model.name("Admin");
        String str = am.userDel(Integer.parseInt(user_id));
        Load.view.out(str);
    }

}
