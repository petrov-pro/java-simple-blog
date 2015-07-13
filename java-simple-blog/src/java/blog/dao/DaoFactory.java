/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import blog.dao.impl.*;

/**
 *
 * @author petroff
 */
public class DaoFactory {
	
	static final String implPath = "blog.dao.impl.";

	public static Object getDao(String class_name) {
		

		Class c = null;
		try {
			c = Class.forName(implPath + class_name);
		} catch (ClassNotFoundException cN) {
		}
		Object obj = null;
		try {
			obj = c.newInstance();
		} catch (InstantiationException ie) {
		} catch (IllegalAccessException il) {
		}
		return obj;

	}
}
