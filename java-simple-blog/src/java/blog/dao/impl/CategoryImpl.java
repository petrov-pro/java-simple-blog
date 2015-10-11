/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao.impl;

import blog.entity.Category;
import blog.entity.Content;
import blog.entity.User;
import blog.system.dao.AbstractDaoImpl;
import blog.system.dao.DaoFactory;
import blog.system.exception.PersistException;
import blog.system.loader.Load;
import blog.system.tools.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author petroff
 */
public class CategoryImpl extends AbstractDaoImpl<Category> {

    @Override
    public String queryFindAll() throws PersistException {
        return "SELECT * FROM blogj.category cat INNER JOIN blogj.content con"
                + "ON cat.id = con.object_id AND con.`type` = '" + Category.getTypeS() + "' AND lang = '"
                + Load.config.getDefaultLang() + "'";
    }

    @Override
    public void prepareQuery(PreparedStatement statement, int pid) throws PersistException {
        try {
            statement.setString(1, Category.getTypeS());
            statement.setInt(2, pid);
            statement.setInt(3, Load.auth.getUserId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void prepareQuery(PreparedStatement statement, Category c) throws PersistException {
        try {
            statement.setBoolean(1, c.isEnable());
            statement.setString(2, c.getAlias());
            statement.setInt(3, c.getWeight());
            statement.setInt(4, Load.auth.getUserId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void prepareQueryUpdate(PreparedStatement statement, Category c) throws PersistException {
        try {
            statement.setBoolean(1, c.isEnable());
            statement.setString(2, c.getAlias());
            statement.setInt(3, c.getWeight());
            statement.setInt(4, c.getId());

        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public List<Category> parseResultSet(ResultSet rs) throws PersistException {
        List<Category> listCategories = new ArrayList();
        Category category;
        try {
            while (rs.next()) {
                category = new Category();
                category.setId(rs.getInt(1));
                listCategories.add(category);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return listCategories;
    }

    @Override
    public String queryFindByPk() throws PersistException {
        return "SELECT * FROM blogj.category cat INNER JOIN blogj.content con"
                + " ON cat.id = con.object_id AND con.`type` = ? AND cat.id = ? AND cat.user_id = ?;";
    }

    @Override
    public String queryUpdate() throws PersistException {
        return "UPDATE blogj.category SET enable = ?, alias = ?, weight = ? WHERE id = ? AND user_id = ?";
    }

    @Override
    public String queryInsert() throws PersistException {
        return "INSERT blogj.category (enable, alias, weight, user_id) VALUE(?, ?, ?, ?);";
    }

    @Override
    public String queryDelete() throws PersistException {
        return "DELETE c, con FROM blogj.category c inner join blogj.content con ON c.id = con.object_id and con.`type` = ?  WHERE c.id = ? and c.user_id = ?;";
    }

    @Override
    public Integer insert(Category category) throws PersistException {
        Integer res;
        super.startTransaction();
        res = super.insert(category);
        try {
            if (res != null) {
                category.setId(res);
                if (!insertContent(category)) {
                    throw new PersistException("Can't insert content");
                }
            }
            super.endTransaction();
        } catch (PersistException p) {
            super.rollbackTransaction();
            throw p;
        }
        return res;
    }

    public boolean insertContent(Category category) throws PersistException {
        ContentImpl contentImpl = (ContentImpl) DaoFactory.getDao("ContentImpl");

        for (Map.Entry<String, String> entry : category.translate.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            Content content = new Content();
            content.setType(category.getType());
            content.setObject_id(category.getId());
            content.setUser_id(Load.auth.getUserId());
            content.setLang(key);
            content.setText(value);
            Integer res = contentImpl.insert(content);
            if (res == null) {
                throw new PersistException("Can't insert content");
            }
        }

        return true;
    }

    public List<Category> findAllForUser(int userId) throws PersistException {

        List<Category> categories = new ArrayList();
        String sql = "SELECT cat.*, con.* FROM blogj.category cat INNER JOIN blogj.content con"
                + " ON cat.id = con.object_id AND con.`type` = ? AND lang = ? AND user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, Category.getTypeS());
            statement.setString(2, Load.lang.get());
            statement.setInt(3, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt(1));
                category.setEnable(rs.getBoolean(2));
                category.setAlias(rs.getString(3));
                category.setWeight(rs.getInt(4));
                String lang = rs.getString(8);
                String text = rs.getString(7);
                category.translate.put(lang, text);
                categories.add(category);
            }
            rs.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return categories;
    }

    public Category findAllForPk(int category_id) throws PersistException {
        Category category = new Category();
        String sql = "SELECT * FROM blogj.category cat INNER JOIN blogj.content con ON cat.id = con.object_id AND con.`type` = ? AND cat.id = ? AND cat.user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, Category.getTypeS());
            statement.setInt(2, category_id);
            statement.setInt(3, Load.auth.getUserId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                category.setId(rs.getInt(1));
                category.setEnable(rs.getBoolean(2));
                category.setAlias(rs.getString(3));
                category.setWeight(rs.getInt(4));
                String lang = rs.getString(8);
                String text = rs.getString(7);
                category.translate.put(lang, text);
            }
            rs.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return category;
    }

    @Override
    public boolean update(Category category) throws PersistException {
        boolean res;
        super.startTransaction();
        res = super.update(category);
        try {
            if (res) {
                if (!updateContent(category)) {
                    throw new PersistException("Can't insert content");
                }
            }
            super.endTransaction();
        } catch (PersistException p) {
            super.rollbackTransaction();
            throw p;
        }
        return res;
    }

    public boolean updateContent(Category category) throws PersistException {
        ContentImpl contentImpl = (ContentImpl) DaoFactory.getDao("ContentImpl");

        for (Map.Entry<String, String> entry : category.translate.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            Content content = new Content();
            content.setType(category.getType());
            content.setObject_id(category.getId());
            content.setUser_id(Load.auth.getUserId());
            content.setLang(key);
            content.setText(value);
            boolean res = contentImpl.update(content);
            if (!res) {
                throw new PersistException("Can't update content");
            }
        }

        return true;
    }

}
