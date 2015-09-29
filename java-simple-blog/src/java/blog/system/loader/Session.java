/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.loader;

/**
 *
 * @author petroff
 */
public class Session {

	public String get(String key) {
		return (String) Load.request.getSession().getAttribute(key);
	}

	public void set(String key, String value) {
		Load.request.getSession().setAttribute(key, value);
	}
}
