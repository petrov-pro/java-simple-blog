/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.model;

import blog.system.tools.Navigator;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author petroff
 */
public abstract class Model implements ModelIntf {

	public Navigator navigator;

	public HttpServletRequest request;

	public ResourceBundle bundle;

	@Override
	public void setNavigator(Navigator navigator) {
		this.navigator = navigator;
	}

	@Override
	public void init(HttpServletRequest r) {
		this.request = r;
		Locale locale = this.request.getLocale();
		this.bundle = ResourceBundle.getBundle("blog.messages.messages", locale);
	}

	@Override
	public void init(Object[] params) {

	}

}
