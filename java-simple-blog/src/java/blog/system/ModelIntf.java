/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system;

import blog.tools.Navigator;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author petroff
 */
public interface ModelIntf<T> {

	public String getView();

	public T getData();

	public void setNavigator(Navigator navigator);

	public T getNavigator();

	public void init(HttpServletRequest r);
}
