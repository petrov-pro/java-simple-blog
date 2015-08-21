/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.system.ControllerImpl;
import blog.system.annotation.Get;
import blog.system.annotation.Post;

/**
 *
 * @author petroff
 */
public class LoginController extends ControllerImpl<LoginController> {

	@Override
	public LoginController index() {
		return this;
	}


	public void login() {

	}

	@Post
	public void login(blog.system.Post post) {

	}
	
	@Get
	public void login(blog.system.Get get) {

	}

}
