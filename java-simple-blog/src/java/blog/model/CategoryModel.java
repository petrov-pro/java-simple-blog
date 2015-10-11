/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

import blog.bind.CategoryBind;
import blog.dao.impl.CategoryImpl;
import blog.entity.Category;
import blog.system.dao.DaoFactory;
import blog.system.exception.PersistException;
import blog.system.loader.Load;
import blog.system.model.Model;
import blog.system.tools.Logger;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.json.simple.JSONObject;

/**
 *
 * @author petroff
 */
//test
public class CategoryModel extends Model {

    private String errorMessage = "";

    private Category category;

    private List<Category> categories;

    public CategoryModel() {
        category = new Category();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Object getData() {
        return this;
    }

    @Override
    public String getView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getNavigator() {
        return null;
    }

    public CategoryModel listCategory() {
        CategoryImpl ci = (CategoryImpl) DaoFactory.getDao("CategoryImpl");
        try {
            categories = ci.findAll();
        } catch (PersistException p) {
            throw new RuntimeException();
        }
        return this;
    }

    public boolean create() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        CategoryBind.bind(category);
        if (Category.validate(category, validator)) {
            CategoryImpl ci = (CategoryImpl) DaoFactory.getDao("CategoryImpl");
            Integer result;
            try {
                result = ci.insert(category);
            } catch (PersistException p) {
                Logger.write(p.toString());
                result = null;
            }
            if (result == null) {
                errorMessage = Load.bundle.getString("category_cant_insert");
                return false;
            } else {
                return true;
            }
        } else {
            errorMessage = Category.getErrorMessage();
            return false;
        }
    }

    public boolean update(String category_id) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        CategoryBind.bind(category);
        if (Category.validate(category, validator)) {
            CategoryImpl ci = (CategoryImpl) DaoFactory.getDao("CategoryImpl");
            boolean result;
            try {
                result = ci.update(category);
            } catch (PersistException p) {
                Logger.write(p.toString());
                result = false;
            }
            if (!result) {
                errorMessage = Load.bundle.getString("category_cant_insert");
                return false;
            } else {
                return true;
            }
        } else {
            errorMessage = Category.getErrorMessage();
            return false;
        }
    }

    public void findAll() {
        CategoryImpl ci = (CategoryImpl) DaoFactory.getDao("CategoryImpl");
        try {
            categories = ci.findAllForUser(Load.auth.getUserId());
        } catch (PersistException p) {
            Logger.write(p.toString());
        }
    }

    public String del(int user_id) {
        Boolean message = false;
        JSONObject resultJson = new JSONObject();
        CategoryImpl ci = (CategoryImpl) DaoFactory.getDao("CategoryImpl");
        try {
            message = ci.delete(user_id);
        } catch (PersistException p) {
            Logger.write(p.toString());
        }
        resultJson.put("message", message);
        return resultJson.toString();
    }

    public void findAllForPk(Integer category_id) {
        CategoryImpl ci = (CategoryImpl) DaoFactory.getDao("CategoryImpl");
        try {
            category = ci.findAllForPk(category_id);
        } catch (PersistException p) {
            Logger.write(p.toString());
        }

    }

}
