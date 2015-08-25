/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.controller;

import blog.system.loader.Load;
import blog.system.tools.ErrorPage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author petroff
 */
public abstract class ControllerImpl<T> extends HttpServlet implements ControllerIntf<T> {
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ErrorPage errorPage;
	
	public void init(HttpServletRequest request, HttpServletResponse response, ErrorPage errorPage) {
		this.request = request;
		this.response = response;
		this.errorPage = errorPage;
		Load.init(request, response, errorPage);
	}
	
	@Override
	public void getView(String path) {
		Load.view.name(path);
	}
}
