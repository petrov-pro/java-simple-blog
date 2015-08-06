/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import java.sql.Connection;
import javax.servlet.ServletContext;

/**
 *
 * @author petroff
 */
public class DaoFactory {

	private static final String implPath = "blog.dao.impl.";
	private static Connection connection;

	public static void setConnection(ServletContext context) {
		connection = (Connection) context.getAttribute("db_connection");
	}

	public static void setConnection(Connection connect) {
		connection = connect;
	}

	public static DaoGeneric getDao(String class_name) {

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
		DaoGeneric DG = (DaoGeneric) obj;
		DG.setConnection(connection);
		return DG;

	}
}
