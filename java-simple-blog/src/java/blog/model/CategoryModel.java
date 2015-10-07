/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

import blog.bind.CategoryBind;
import blog.dao.impl.CategoryImpl;
import blog.dao.impl.UserImpl;
import blog.entity.Category;
import blog.system.dao.DaoFactory;
import blog.system.exception.PersistException;
import blog.system.loader.Load;
import blog.system.model.Model;
import blog.system.tools.BindParams;
import blog.system.tools.Logger;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author petroff
 */
//test
public class CategoryModel extends Model {

    private String errorMessage = "";

    private Category category;

    private List<Category> сategories;

    public CategoryModel() {
        category = new Category();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getСategories() {
        return сategories;
    }

    public void setСategories(List<Category> сategories) {
        this.сategories = сategories;
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
            сategories = ci.findAll();
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
            Long result;
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

}
