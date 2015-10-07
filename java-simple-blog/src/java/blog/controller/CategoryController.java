/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.model.CategoryModel;
import blog.system.annotation.Get;
import blog.system.annotation.Post;
import blog.system.controller.ControllerImpl;
import blog.system.loader.Load;
import blog.system.tools.Http;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author petroff
 */
public class CategoryController extends ControllerImpl<CategoryController> {

    @Override
    public void index() throws ServletException, IOException {
        Http.redirect();
    }

    @Get
    public void create(blog.system.environment.Get get) throws ServletException, IOException {
        Load.view.name("/category/create.jsp");
    }

    @Post
    public void create(blog.system.environment.Post post) throws ServletException, IOException {
        CategoryModel categoryModel = (CategoryModel) Load.model.name("Category");

        if (categoryModel.create()) {
            Http.redirect("/category/done");
        } else {
            super.request.setAttribute("Data", categoryModel);
            Load.view.name("/category/create.jsp");
        }

    }

}
