/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.tools;

/**
 *
 * @author petroff
 */
import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbManager {

    private DbManager() {
    }

    public static Connection getConnection(String ConnectorName) {
        try {
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup(ConnectorName);
            Connection connection = ds.getConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
