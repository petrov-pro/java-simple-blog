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
import blog.system.tools.Logger;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class MainListener implements ServletRequestListener {

    private ServletContext context = null;

    public MainListener() {
    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        context = event.getServletContext();
        InitBundle(event);

    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        Connection c = DaoFactory.getConnection();
        try {
            if (c != null && !c.isClosed()) {
                Connection dbConnection = DaoFactory.getConnection();
                try {
                    dbConnection.close();
                    Logger.write("Connection close");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                Logger.write("Can't find connection");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InitBundle(ServletRequestEvent event) {
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
        Load.initBundle(request);
    }

}
