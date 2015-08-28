/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.listener;

/**
 *
 * @author petroff
 */
import blog.system.dao.DaoFactory;
import blog.system.loader.Load;
import blog.system.tools.DbManager;
import blog.system.tools.ErrorPage;
import java.sql.Connection;
import java.sql.SQLException;
import javax.inject.Inject;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebListener
public class MainListener implements ServletRequestListener {

	private ServletContext context = null;


	public MainListener() {
	}

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		context = event.getServletContext();
		initLoader(event);
		InitializeDbConnection(context);

	}

	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		context = event.getServletContext();
		if (context.getAttribute("db_connection") != null) {
			Connection dbConnection = (Connection) context.getAttribute("db_connection");
			try {
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void InitializeDbConnection(ServletContext context) {
		String DbConnectorName = context.getInitParameter("DbConnectorName");
		Connection dbConnection = DbManager.getConnection(DbConnectorName);
		context.setAttribute("db_connection", dbConnection);
		DaoFactory.setConnection(context);

	}

	public void initLoader(ServletRequestEvent event) {

		HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
		HttpServletResponse response = (HttpServletResponse) this.response;
		ErrorPage errorPage = new ErrorPage(request, (HttpServletResponse)response);
		Load load = new Load(request, response, errorPage);
		context.setAttribute("Load", load);
	}

}
