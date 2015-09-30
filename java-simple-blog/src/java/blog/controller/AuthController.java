/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.system.controller.ControllerImpl;
import blog.system.annotation.Get;
import blog.system.annotation.Post;
import blog.system.loader.Load;
import blog.system.tools.Http;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author petroff
 */
public class AuthController extends ControllerImpl<AuthController> {

	@Override
	public void index() throws ServletException, IOException {
		Http.redirect("/main/");
	}

	public void login() throws ServletException, IOException {
		Http.redirect("/main/");
	}

	@Post
	public void login(blog.system.environment.Post post) throws ServletException, IOException {
		Http.redirect("/main/");
	}

	@Get
	public void login(blog.system.environment.Get get) throws ServletException, IOException {
		Http.redirect("/main/");
	}

	public void logout() throws ServletException, IOException {
		Load.request.getSession().invalidate();
		Load.request.logout();
		Http.redirect("/main/");
	}

}
