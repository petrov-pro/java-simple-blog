/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.system.controller.ControllerImpl;
import blog.system.annotation.Get;
import blog.system.annotation.Post;
import blog.system.loader.Load;

/**
 *
 * @author petroff
 */
public class AuthController extends ControllerImpl<AuthController> {

	@Override
	public AuthController index() {
		return this;
	}

	public void login() {

	}

	@Post
	public void login(blog.system.environment.Post post) {
		
	}

	@Get
	public void login(blog.system.environment.Get get) {
		Load.view.name("/auth/login.jsp");
	}

}
