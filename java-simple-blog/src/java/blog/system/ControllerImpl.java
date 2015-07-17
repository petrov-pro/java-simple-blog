/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system;

import blog.tools.ErrorPage;
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
	final String basePath = "/WEB-INF/views/";

	public void init(HttpServletRequest request, HttpServletResponse response, ErrorPage errorPage) {
		this.request = request;
		this.response = response;
		this.errorPage = errorPage;
	}

	@Override
	public void getView(String path) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.getRequestDispatcher(basePath + path).forward(request, response);
		} catch (Exception e) {
			errorPage.show404(e);
		}
	}
}
