/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

import blog.bind.ArticleBind;
import blog.dao.impl.ArticleImpl;
import blog.entity.Article;
import blog.entity.Tag;
import blog.model.intf.TreeIntf;
import blog.system.dao.DaoFactory;
import blog.system.exception.PersistException;
import blog.system.loader.Load;
import blog.system.tools.Navigator;
import blog.system.model.Model;
import blog.system.tools.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.json.simple.JSONObject;

/**
 *
 * @author petroff
 */
public class ArticleModel extends Model {

    private String errorMessage = "";
    private String url = "/article/create/";

    private Article article;
    private List<Article> articles;
    private TreeIntf tree;

    private String tagsStr;

    private int count;

    private String search = "null";

    public ArticleModel() {
        article = new Article();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public TreeIntf getTree() {
        return tree;
    }

    public void setTree(TreeIntf tree) {
        this.tree = tree;
    }

    public String getTagsStr() {
        return tagsStr;
    }

    public void setTagsStr(String tagsStr) {
        this.tagsStr = tagsStr;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Article getArticle() {
        return article;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArticleModel getData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Navigator getNavigator() {
        return this.navigator;
    }

    public boolean update(String articleId) {
        url = "/article/update/" + articleId;
        ArticleBind.bind(article, articleId);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        if (Article.validate(article, validator)) {
            ArticleImpl i = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
            TagModel tagModel = (TagModel) Load.model.name("Tag");
            article.setUser_id(Load.auth.getUserId());
            boolean result;
            boolean resultTag = false;
            try {
                i.startTransaction();
                result = i.update(article);
                resultTag = tagModel.update(article.getTagsStr(), article.getId(), article.getUser_id());
            } catch (PersistException p) {
                Logger.write(p.toString());
                result = false;
            }
            if (!result) {
                errorMessage = Load.bundle.getString("article_cant_update");
                return false;

            } else if (!resultTag) {
                errorMessage = Load.bundle.getString("tag_cant_insert");
                return false;
            } else {
                try {
                    i.endTransaction();
                } catch (PersistException p) {
                    Logger.write(p.toString());
                    return false;
                }
                return true;
            }
        } else {
            errorMessage = Article.getErrorMessage();
            return false;
        }
    }

    public void findAll() {
        ArticleImpl ai = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
        try {
            articles = ai.findAllForUser(Load.auth.getUserId());
        } catch (PersistException p) {
            Logger.write(p.toString());
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean create() {
        ArticleBind.bind(article);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        TagModel tagModel = (TagModel) Load.model.name("Tag");
        if (Article.validate(article, validator)) {
            ArticleImpl ci = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
            Integer result;
            article.setUser_id(Load.auth.getUserId());
            boolean resultTag = false;
            try {
                ci.startTransaction();
                result = ci.insert(article);
                resultTag = tagModel.update(article.getTagsStr(), article.getId(), article.getUser_id());

            } catch (PersistException p) {
                Logger.write(p.toString());
                result = null;
            }
            if (result == null) {
                errorMessage = Load.bundle.getString("article_cant_insert");
                return false;
            } else if (!resultTag) {
                errorMessage = Load.bundle.getString("tag_cant_insert");
                return false;
            } else {
                try {
                    ci.endTransaction();
                } catch (PersistException p) {
                    Logger.write(p.toString());
                    return false;
                }
                return true;
            }
        } else {
            errorMessage = Article.getErrorMessage();
            return false;
        }
    }

    public void findByPk(Integer articleId) {
        url = "/article/update/" + articleId;
        ArticleImpl ai = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
        TagModel tagModel = (TagModel) Load.model.name("Tag");
        try {
            article = ai.findByPk(articleId);
        } catch (PersistException p) {
            Logger.write(p.toString());
        }

        String tagsStr = tagModel.getTagsStr(article.getUser_id(), article.getId());
        article.setTagsStr(tagsStr);

    }

    @Override
    public boolean checkUnique(String name) {
        ArticleImpl i = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
        try {
            if (article != null) {
                int count = i.findByAlias(article.getAlias(), article.getId());
                if (count == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (PersistException p) {
            Logger.write(p.toString());
            return false;
        }

    }

    public String del(int articleId) {
        Boolean message = false;
        boolean res = false;
        JSONObject resultJson = new JSONObject();
        ArticleImpl ai = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
        TagModel tagModel = (TagModel) Load.model.name("Tag");
        try {
            ai.startTransaction();
            res = tagModel.dellByArticleUser(articleId, Load.auth.getUserId());
            message = ai.delete(articleId);
        } catch (PersistException p) {
            Logger.write(p.toString());
        }
        try {
            if (message) {
                ai.endTransaction();
            } else {
                ai.rollbackTransaction();
            }
        } catch (PersistException p) {
            Logger.write(p.toString());
        }
        resultJson.put("message", message);
        return resultJson.toString();
    }

    public void findAllForMain() {
        _findAllForMain(null);
    }

    public void findAllForMain(Integer category_id) {
        _findAllForMain(category_id);
    }

    public void _findAllForMain(Integer category_id) {
        ArticleImpl ai = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
        TagModel tagModel = (TagModel) Load.model.name("Tag");
        try {
            articles = ai.findAllForMain(category_id);
            for (Article article : articles) {
                ArrayList<Tag> tags = (ArrayList) tagModel.findByPkForUser(article.getUser_id(), article.getId());
                article.setTags(tags);
            }
        } catch (PersistException p) {
            Logger.write(p.toString());
        }

    }

    public void findByAliasUser(String user_id_raw, String article_alias) {
        ArticleImpl ai = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
        TagModel tagModel = (TagModel) Load.model.name("Tag");
        try {
            int user_id = Integer.parseInt(user_id_raw);
            article = ai.findByAliasUser(user_id, article_alias);
            ArrayList<Tag> tags = (ArrayList) tagModel.findByPkForUser(article.getUser_id(), article.getId());
            article.setTags(tags);
        } catch (PersistException p) {
            Logger.write(p.toString());
        }

    }

    public void findAllForCategory(Integer category_id) {
        ArticleImpl ai = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
        TagModel tagModel = (TagModel) Load.model.name("Tag");
        try {
            articles = ai.findAllForMain(category_id);
            for (Article article : articles) {
                ArrayList<Tag> tags = (ArrayList) tagModel.findByPkForUser(article.getUser_id(), article.getId());
                article.setTags(tags);
            }
        } catch (PersistException p) {
            Logger.write(p.toString());
        }

    }

    public void findAllForUser(int userId) {
        ArticleImpl ai = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
        try {
            articles = ai.findAllForUser(userId);
        } catch (PersistException p) {
            Logger.write(p.toString());
        }
    }

    public void findAllCustom(int page, String searchL) {
        ArticleImpl ai = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
        TagModel tagModel = (TagModel) Load.model.name("Tag");
        try {
            articles = ai.findAllCustom(page, searchL);
            for (Article article : articles) {
                ArrayList<Tag> tags = (ArrayList) tagModel.findByPkForUser(article.getUser_id(), article.getId());
                article.setTags(tags);
            }
            count(searchL);
        } catch (PersistException p) {
            Logger.write(p.toString());
        }

    }

    public int count(String searchL) {
        ArticleImpl ci = (ArticleImpl) DaoFactory.getDao("ArticleImpl");
        try {
            count = ci.count(searchL);
        } catch (PersistException p) {
            Logger.write(p.toString());
        }
        float count_t;
        count_t = (float) count / Load.config.limit;
        count = (int) Math.round(count_t);
        return count;
    }
}
