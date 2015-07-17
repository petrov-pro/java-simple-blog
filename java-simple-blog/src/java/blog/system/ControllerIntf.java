/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system;

import blog.tools.ErrorPage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author petroff
 */
public interface ControllerIntf<T> {


	public void getView(String path);

	public void init(HttpServletRequest request, HttpServletResponse response, ErrorPage errorPage);

	public T index();
}
