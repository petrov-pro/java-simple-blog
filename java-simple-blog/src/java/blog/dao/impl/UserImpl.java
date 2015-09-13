/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao.impl;

import blog.system.dao.AbstractDaoImpl;
import blog.entity.User;
import blog.system.exception.PersistException;
import blog.system.tools.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author petroff
 */
public class UserImpl extends AbstractDaoImpl<User> {

    @Override
    public String queryFindAll() throws PersistException {
        return "SELECT * FROM blogj.users;";
    }

    @Override
    public void prepareQuery(PreparedStatement statement, int pid) throws PersistException {
        try {
            statement.setInt(1, pid);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void prepareQuery(PreparedStatement statement, User a) throws PersistException {
        try {
            //statement.setInt(1, pid);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public List<User> parseResultSet(ResultSet rs) throws PersistException {
        List<User> listUsers = new ArrayList();
        User user = new User();
        try {
            rs.next();
            user.setId(rs.getInt("id"));
            user.setUser_name(rs.getString("user_name"));
            user.setEmail(rs.getString("email"));
            listUsers.add(user);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return listUsers;
    }

    @Override
    public String queryFindByPk() throws PersistException {
        return "SELECT * FROM blogj.users WHERE id = ?";
    }

    @Override
    public String queryUpdate() throws PersistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String queryInsert() throws PersistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String queryDelete() throws PersistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String queryFindByName() throws PersistException {
        return "";
    }

    public User findByUserName(String username) {
        User user = new User();
        String sql = "SELECT * FROM blogj.users WHERE user_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();
            rs.next();
            user.setId(rs.getInt("id"));
            user.setUser_name(rs.getString("user_name"));
            user.setEmail(rs.getString("email"));
        } catch (Exception e) {
            Logger.write("error findByUserName" + e.toString());
        }
        return user;
    }

}
