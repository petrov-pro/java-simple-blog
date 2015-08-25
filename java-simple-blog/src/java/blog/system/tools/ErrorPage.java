/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.tools;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author petroff
 */
public class ErrorPage {

	private String path404 = "/WEB-INF/views/404.jsp";
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ErrorPage(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public void show404(Exception e) {
		try {
			request.setAttribute("Error", e);
			request.getRequestDispatcher(path404).forward(request, response);
		} catch (ServletException | IOException eg) {
		}

	}

	public String getPath404() {
		return path404;
	}

	public void setPath404(String path404) {
		this.path404 = path404;
	}
}
