/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.system.controller.ControllerImpl;
import blog.system.annotation.Get;
import blog.system.annotation.Post;
import blog.system.loader.Load;
import java.io.IOException;
import java.net.URI;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

/**
 *
 * @author petroff
 */
public class AuthController extends ControllerImpl<AuthController> {

    @Override
    public void index() throws ServletException, IOException {
        reidrect();
    }

    public void login() throws ServletException, IOException {
        reidrect();
    }

    @Post
    public void login(blog.system.environment.Post post) throws ServletException, IOException {
        reidrect();
    }

    @Get
    public void login(blog.system.environment.Get get) throws ServletException, IOException {
        reidrect();
    }

    public void logout() throws ServletException, IOException {
        Load.request.getSession().invalidate();
        Load.request.logout();
        reidrect();
    }

    private void reidrect() throws ServletException, IOException {
        String url = Load.request.getHeader("referer");
        if (url == null) {
            url = Load.config.urlDefault;
        }

        String urlWithSessionID = Load.response.encodeRedirectURL(url);
        Load.response.sendRedirect(urlWithSessionID);

    }

}
