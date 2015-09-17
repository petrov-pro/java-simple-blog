/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.model.user.UserModel;
import blog.system.annotation.Get;
import blog.system.annotation.Post;
import blog.system.controller.ControllerImpl;
import blog.system.loader.Load;
import blog.system.tools.Http;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author petroff
 */
public class UserController extends ControllerImpl<UserController> {

    @Override
    public void index() throws ServletException, IOException {
        Http.redirect();
    }

    public void done() {
        Load.view.name("/user/done.jsp");
    }

    public void registration() {
        Load.view.name("/user/registration.jsp");
    }

    @Get
    public void registration(blog.system.environment.Get get) throws ServletException, IOException {
        Load.view.name("/user/registration.jsp");
    }

    @Post
    public void registration(blog.system.environment.Post post) throws ServletException, IOException {
        UserModel userModel = (UserModel) Load.model.name("User");
        if (userModel.create()) {
            Http.redirect("/user/done");
        } else {
            super.request.setAttribute("Data", userModel);
            Load.view.name("/user/registration.jsp");
        }
    }

    @Get
    public void privat(blog.system.environment.Get get) throws ServletException, IOException {
        UserModel userModel = (UserModel) Load.model.name("User");
        int user_id = Load.auth.getUserId();
        userModel.getUserInfo(user_id);
        super.request.setAttribute("Data", userModel);
        Load.view.name("/user/privat.jsp");
    }

    @Post
    public void privat(blog.system.environment.Post post) throws ServletException, IOException {

        UserModel userModel = (UserModel) Load.model.name("User");
        if (userModel.update(Load.auth.getUserId())) {
            Http.redirect("/user/done");
        } else {
            super.request.setAttribute("Data", userModel);
            Load.view.name("/user/privat.jsp");
        }
    }

}
