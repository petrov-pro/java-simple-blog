/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao.impl;

import blog.entity.Article;
import blog.entity.Category;
import blog.system.dao.AbstractDaoImpl;
import blog.entity.Tag;
import blog.system.exception.PersistException;
import blog.system.loader.Load;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author petroff
 */
public class TagImpl extends AbstractDaoImpl<Tag> {

    @Override
    public String queryFindAll() throws PersistException {
        return "SELECT * FROM blogj.tag;";
    }

    @Override
    public void prepareQuery(PreparedStatement statement, int pid) throws PersistException {
        try {
            statement.setInt(1, pid);
            statement.setInt(2, Load.auth.getUserId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void prepareQuery(PreparedStatement statement, Tag t) throws PersistException {
        try {
            statement.setString(1, t.getName());
            statement.setInt(2, Load.auth.getUserId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public List<Tag> parseResultSet(ResultSet rs) throws PersistException {
        List<Tag> listTags = new ArrayList();
        Tag tag = new Tag();
        try {
            rs.next();

            listTags.add(tag);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return listTags;
    }

    @Override
    public String queryFindByPk() throws PersistException {
        return "SELECT * FROM blogj.tag WHERE id = ?";
    }

    @Override
    public String queryUpdate() throws PersistException {
        return "UPDATE blogj.tag SET enable = ?, alias = ?, weight = ? WHERE id = ? AND user_id = ?";
    }

    @Override
    public String queryInsert() throws PersistException {
        return "INSERT blogj.tag (name, user_id) VALUE(?, ?);";
    }

    @Override
    public String queryDelete() throws PersistException {
        return "DELETE c, con FROM blogj.category c inner join blogj.content con ON c.id = con.object_id and (con.`type` = 'tag_t' or con.`type` = 'tag_b')  WHERE c.id = ? and c.user_id = ?;";
    }

    @Override
    public void prepareQueryUpdate(PreparedStatement statement, Tag entity) throws PersistException {
        try {
            statement.setInt(4, entity.getId());
            statement.setInt(5, Load.auth.getUserId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public Integer insert(Tag tag) throws PersistException {
        Integer res = 0;
        res = super.insert(tag);
        if (res != null) {
            tag.setId(res);
        }
        return res;
    }

    @Override
    public boolean update(Tag tag) throws PersistException {
        boolean res = false;
        res = super.update(tag);
        return res;
    }

    public Tag findByName(String tagName, int userId) throws PersistException {
        Tag tag = null;
        String sql = "select t.* from blogj.tag t  where  t.name = ? and t.user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tagName);
            statement.setInt(2, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tag = new Tag();
                tag.setId(rs.getInt("id"));
                String text = rs.getString("name");//not text
                tag.setName(text);
            }
            rs.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return tag;
    }

    public Integer insert_link(Tag tag, int articleId) throws PersistException {
        String sql = "INSERT blogj.article_tag_link (article_id, tag_id) VALUE(?, ?);";
        int affectedRows;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, articleId);
            statement.setInt(2, tag.getId());
            affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                statement.close();
                throw new PersistException("Insert failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Integer last_id = generatedKeys.getInt(1);
                    statement.close();
                    return last_id;
                } else {
                    statement.close();
                    throw new PersistException("Get result failed, no ID obtained.");
                }
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public boolean dellByArticleUser(int articleId, int userId) throws PersistException {
        String sql = "DELETE FROM blogj.article_tag_link WHERE article_id = (SELECT id FROM blogj.article WHERE id = ? AND user_id = ?);";
        int rs;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, articleId);
            statement.setInt(2, userId);
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

    public List<Tag> findByPkForUser(int userId, int articleId) throws PersistException {
        List<Tag> tags = new ArrayList();
        String sql = "SELECT * FROM blogj.tag t INNER JOIN blogj.article_tag_link al"
                + " WHERE t.id = al.tag_id AND al.article_id = ? AND t.user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, articleId);
            statement.setInt(2, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getInt("id"));
                tag.setName(rs.getString("name"));
                tag.setUser_id(rs.getInt("user_id"));
                tags.add(tag);
            }
            rs.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return tags;
    }

    public Tag findByName(String tag_name) throws PersistException {
        Tag tag = new Tag();
        String sql = "SELECT t.id, t.`name` as tag_name, t.user_id, a.id as article_id,a.alias as article_alias,"
                + " a.title, a.body, a.ut as article_ut, "
                + "a.lang, c.id as category_id, c.title_category, c.alias as category_alias, "
                + "u.user_name "
                + "FROM blogj.tag t  "
                + "INNER JOIN blogj.article_tag_link al "
                + "ON t.id = al.tag_id "
                + "INNER JOIN  "
                + "(SELECT t.*, title.text as title, body.text as body, title.lang as lang  "
                + "FROM blogj.article t   "
                + "                INNER JOIN blogj.content title  "
                + "                ON t.id = title.object_id AND title.`type` = 'article_t'   "
                + "                INNER JOIN blogj.content body  "
                + "                ON t.id = body.object_id AND body.`type` = 'article_b'  "
                + "AND body.lang = title.lang  "
                + "                WHERE t.enable = true "
                + "                 GROUP BY t.id)  "
                + "AS a ON a.id = al.article_id "
                + "INNER JOIN ( "
                + "SELECT cat.*, con.text as title_category, con.lang FROM blogj.category cat   "
                + "                INNER JOIN blogj.content con   "
                + "                ON cat.id = con.object_id AND con.`type` = 'category'  "
                + " WHERE enable=true  "
                + ") c  "
                + "ON c.id = a.category_id AND c.lang = a.lang "
                + "INNER JOIN blogj.users u  "
                + "ON t.user_id = u.id   "
                + "WHERE t.`name` = ? AND a.lang = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tag_name);
            statement.setString(2, Load.lang.get());
            ResultSet rs = statement.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Article article = new Article();
                    Category category = new Category();
                    article.setId(rs.getInt("article_id"));
                    article.setAlias(rs.getString("article_alias"));
                    article.setCategory_id(rs.getInt("category_id"));
                    article.setUt(rs.getString("article_ut"));
                    article.setUser_id(rs.getInt("user_id"));
                    String lang = rs.getString("lang");
                    String title = rs.getString("title");
                    String body = rs.getString("body");
                    article.translate_title.put(lang, title);
                    article.translate_body.put(lang, body);
                    article.setTags((ArrayList) findByPkForUser(article.getUser_id(), article.getId()));
                    category.setId(rs.getInt("category_id"));
                    category.setAlias(rs.getString("category_alias"));
                    String text = rs.getString("title_category");
                    category.getTranslate().put(lang, text);

                    article.setCategory(category);
                    tag.getArticles().add(article);
                }
                rs.previous();
                tag.setId(rs.getInt("id"));
                tag.setName(rs.getString("tag_name"));
                tag.setUser_id(rs.getInt("user_id"));
                rs.close();
            }

        } catch (Exception e) {
            throw new PersistException(e);
        }
        return tag;
    }

}
