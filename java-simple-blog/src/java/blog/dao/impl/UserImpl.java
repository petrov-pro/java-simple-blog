/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao.impl;

import blog.system.dao.AbstractDaoImpl;
import blog.entity.User;
import blog.system.exception.PersistException;
import blog.system.loader.Load;
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

    private String strUpdate;
    private String strUpdateWithoutPass = "UPDATE blogj.users SET email = ? WHERE id = ?;";
    private String strUpdateWithPass = "UPDATE blogj.users SET email = ?, password = ? WHERE id = ?;";//test

    public void setStrUpdateWithoutPass() {
        strUpdate = strUpdateWithoutPass;
    }

    public void setStrUpdateWithPass() {
        strUpdate = strUpdateWithPass;
    }

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
    public void prepareQuery(PreparedStatement statement, User u) throws PersistException {
        try {
            statement.setString(1, u.getUser_name());
            statement.setString(2, u.getEmail());

            statement.setString(3, u.getPasswordHash());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void prepareQueryUpdate(PreparedStatement statement, User u) throws PersistException {
        try {
            statement.setString(1, u.getEmail());

            if (!u.getPassword().isEmpty()) {
                statement.setString(2, u.getPasswordHash());
                statement.setInt(3, u.getId());
            } else {
                statement.setInt(2, u.getId());
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public List<User> parseResultSet(ResultSet rs) throws PersistException {
        List<User> listUsers = new ArrayList();
        User user;
        try {
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUser_name(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                listUsers.add(user);
            }
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
        return strUpdate;
    }

    @Override
    public String queryInsert() throws PersistException {
        return "INSERT blogj.users (user_name, email, password) VALUE(?, ?, ?);";
    }

    @Override
    public String queryDelete() throws PersistException {
        return "DELETE  FROM blogj.users u "
                + " WHERE u.id = ?;";
    }

    public String queryFindByName() throws PersistException {
        return "";
    }

    public User findByUserName(String username) {
        User user = new User();
        String sql = "SELECT u.*, g.id as g_id FROM blogj.users u"
                + " INNER JOIN blogj.groups g ON g.user_name = u.user_name"
                + " WHERE u.user_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();
            rs.next();
            user.setId(rs.getInt("id"));
            user.setUser_name(rs.getString("user_name"));
            user.setEmail(rs.getString("email"));
            user.setGroupId(rs.getInt("g_id"));
            rs.close();
        } catch (Exception e) {
            Logger.write("error findByUserName" + e.toString());
            return null;
        }

        return user;
    }

    public Integer insertAdv(User user) throws PersistException {
        Integer res;
        super.startTransaction();
        res = super.insert(user);
        try {
            if (res != null) {
                if (!insertGroup(user.getUser_name())) {
                    throw new PersistException();
                }
            }
            super.endTransaction();
        } catch (PersistException p) {
            super.rollbackTransaction();
            throw p;
        }
        return res;
    }

    public boolean insertGroup(String user_name) throws PersistException {
        String sql = "INSERT INTO blogj.groups  (group_name, descriptor, user_name) VALUE(?,?,?);";
        int rs;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, Load.config.userGroup);
            statement.setString(2, Load.config.userDecriptor);
            statement.setString(3, user_name);
            rs = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }

        if (rs > 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<User> findAllCustom(int page, String search) throws PersistException {
        List<User> users = new ArrayList();
        String sql;
        sql = "SELECT t.* FROM blogj.users t  ";

        if (!search.isEmpty()) {
            sql = sql + " WHERE user_name LIKE ?  ";
        }

        sql = sql + " ORDER BY t.id DESC LIMIT ? OFFSET ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            if (!search.isEmpty()) {
                statement.setString(1, "%" + search + "%");
                statement.setInt(2, Load.config.limit);
                statement.setInt(3, (page - 1) * Load.config.limit);
            } else {
                statement.setInt(1, Load.config.limit);
                statement.setInt(2, (page - 1) * Load.config.limit);
            }

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUser_name(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
            rs.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return users;
    }

    public int count(String search) throws PersistException {
        int count = 0;
        String sql = "SELECT count(t.id) as count FROM blogj.users t  ";
        if (!search.isEmpty()) {
            sql = sql + " WHERE user_name LIKE ? ;";
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            if (!search.isEmpty()) {
                statement.setString(1, "%" + search + "%");
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
            rs.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return count;
    }

}
