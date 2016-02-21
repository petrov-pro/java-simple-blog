/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.loader;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author petroff
 */
public class Bundle {

	public ResourceBundle getBundle(HttpServletRequest r) {
		HttpServletRequest request = r;
		Locale locale = request.getLocale();
		return ResourceBundle.getBundle("blog.messages.messages", locale);
	}

}
