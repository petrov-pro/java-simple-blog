/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao.impl;

import blog.system.dao.AbstractDaoImpl;
import blog.entity.Article;
import blog.entity.Category;
import blog.entity.Content;
import blog.system.dao.DaoFactory;
import blog.system.exception.PersistException;
import blog.system.loader.Load;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author petroff
 */
public class ArticleImpl extends AbstractDaoImpl<Article> {

    @Override
    public String queryFindAll() throws PersistException {
        return "SELECT * FROM blogj.article;";
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
    public void prepareQuery(PreparedStatement statement, Article a) throws PersistException {
        try {
            statement.setBoolean(1, a.isEnable());
            statement.setString(2, a.getAlias());
            statement.setInt(3, a.getWeight());
            statement.setInt(4, Load.auth.getUserId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public List<Article> parseResultSet(ResultSet rs) throws PersistException {
        List<Article> listArticles = new ArrayList();
        Article article = new Article();
        try {
            rs.next();

            listArticles.add(article);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return listArticles;
    }

    @Override
    public String queryFindByPk() throws PersistException {
        return "SELECT * FROM blogj.article WHERE id = ?";
    }

    @Override
    public String queryUpdate() throws PersistException {
        return "UPDATE blogj.article SET enable = ?, alias = ?, weight = ? WHERE id = ? AND user_id = ?";
    }

    @Override
    public String queryInsert() throws PersistException {
        return "INSERT blogj.article (enable, alias, weight, user_id, ut) VALUE(?, ?, ?, ?, NOW());";
    }

    @Override
    public String queryDelete() throws PersistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void prepareQueryUpdate(PreparedStatement statement, Article entity) throws PersistException {
        try {
            statement.setBoolean(1, entity.isEnable());
            statement.setString(2, entity.getAlias());
            statement.setInt(3, entity.getWeight());
            statement.setInt(4, entity.getId());
            statement.setInt(5, Load.auth.getUserId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public Integer insert(Article article) throws PersistException {
        Integer res;
        super.startTransaction();
        res = super.insert(article);
        try {
            if (res != null) {
                article.setId(res);
                if (!insertContent(article)) {
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

    public boolean insertContent(Article article) throws PersistException {
        ContentImpl contentImpl = (ContentImpl) DaoFactory.getDao("ContentImpl");

        for (Map.Entry<String, String> entry : article.translate_title.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            Content content = new Content();
            content.setType(article.getType() + "_t");
            content.setObject_id(article.getId());
            content.setUser_id(Load.auth.getUserId());
            content.setLang(key);
            content.setText(value);
            Integer res = contentImpl.insert(content);
            if (res == null) {
                throw new PersistException("Can't insert content");
            }
        }

        for (Map.Entry<String, String> entry : article.translate_body.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            Content content = new Content();
            content.setType(article.getType() + "_b");
            content.setObject_id(article.getId());
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

    @Override
    public Article findByPk(int article_id) throws PersistException {
        Article article = new Article();
        String sql = "select a.*, co.* from blogj.article a inner join blogj.content co "
                + "ON a.id = co.object_id and (co.`type` = 'article_t' or co.`type` = 'article_b') and a.id = ? and a.user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, article_id);
            statement.setInt(2, Load.auth.getUserId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                article.setId(rs.getInt("id"));
                article.setEnable(rs.getBoolean("enable"));
                article.setAlias(rs.getString("alias"));
                article.setWeight(rs.getInt("weight"));
                String lang = rs.getString("lang");
                String text = rs.getString("text");
                if (rs.getString("type").equals("article_t")) {
                    article.translate_title.put(lang, text);
                } else {
                    article.translate_body.put(lang, text);
                }
            }
            rs.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return article;
    }

    @Override
    public boolean update(Article article) throws PersistException {
        boolean res;
        super.startTransaction();
        res = super.update(article);
        try {
            if (res) {
                if (!updateContent(article)) {
                    throw new PersistException("Can't update content");
                }
            }
            super.endTransaction();
        } catch (PersistException p) {
            super.rollbackTransaction();
            throw p;
        }
        return res;
    }

    public boolean updateContent(Article article) throws PersistException {
        ContentImpl contentImpl = (ContentImpl) DaoFactory.getDao("ContentImpl");

        for (Map.Entry<String, String> entry : article.translate_title.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            Content content = new Content();
            content.setType(article.getType() + "_t");
            content.setObject_id(article.getId());
            content.setUser_id(Load.auth.getUserId());
            content.setLang(key);
            content.setText(value);
            boolean res = contentImpl.update(content);
            if (!res) {
                throw new PersistException("Can't update content");
            }
        }

        for (Map.Entry<String, String> entry : article.translate_body.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            Content content = new Content();
            content.setType(article.getType() + "_b");
            content.setObject_id(article.getId());
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

    public int findByAlias(String alias, int article_id) throws PersistException {
        int count = 0;
        String sql = "SELECT * FROM blogj.article t  WHERE t.alias = ? AND t.user_id = ? and t.id != ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, alias);
            statement.setInt(2, Load.auth.getUserId());
            statement.setInt(3, article_id);
            ResultSet rs = statement.executeQuery();
            rs.last();
            count = rs.getRow();
            rs.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return count;
    }
    
     public List<Article> findAllForUser(int userId) throws PersistException {

        List<Article> categories = new ArrayList();
        String sql = "SELECT t.*, con.* FROM blogj.article t INNER JOIN blogj.content con"
                + " ON t.id = con.object_id AND (co.`type` = 'article_t' or co.`type` = 'article_b') AND lang = ? AND user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, Load.lang.get());
            statement.setInt(2, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt(1));
                article.setEnable(rs.getBoolean(2));
                article.setAlias(rs.getString(3));
                article.setWeight(rs.getInt(4));
                String lang = rs.getString(8);
                String text = rs.getString(7);
                 if (rs.getString("type").equals("article_t")) {
                    article.translate_title.put(lang, text);
                } else {
                    article.translate_body.put(lang, text);
                }
                categories.add(article);
            }
            rs.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return categories;
    }

}
