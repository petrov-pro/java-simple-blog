/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.listener;

/**
 *
 * @author petroff
 */
import blog.dao.DaoFactory;
import blog.tools.DbManager;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MainListener implements ServletRequestListener {

    private ServletContext context = null;

    public MainListener() {
    }
    
    @Override
    public void requestInitialized(ServletRequestEvent event) {
        context = event.getServletContext();
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

}
