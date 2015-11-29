/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.model.CategoryModel;
import blog.model.CommentModel;
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
public class CommentController extends ControllerImpl<CommentController> {

	@Override
	public void index() throws ServletException, IOException {
		Http.redirect();
	}

	@Get
	public void create(blog.system.environment.Get get) throws ServletException, IOException {
		CommentModel commentModel = (CommentModel) Load.model.name("Comment");
		Load.view.name("/comment/save.jsp", commentModel);
	}

	@Post
	public void create(blog.system.environment.Post post) throws ServletException, IOException {
		CommentModel commentModel = (CommentModel) Load.model.name("Comment");

		if (commentModel.create()) {
			Http.redirect("/main/done");
		} else {
			super.request.setAttribute("Data", commentModel);
			Load.view.name("/comment/save.jsp");
		}

	}

	@Get
	public void update(blog.system.environment.Get get, String category_id) throws ServletException, IOException {
		Object[] arg = new Object[]{category_id};
		CategoryModel categoryModel = (CategoryModel) Load.model.name("Category", arg);
		categoryModel.findAllForPk(Integer.parseInt(category_id));
		Load.view.name("/category/save.jsp", categoryModel);
	}

	@Post
	public void update(blog.system.environment.Post post, String category_id) throws ServletException, IOException {
		Object[] arg = new Object[]{category_id};
		CategoryModel categoryModel = (CategoryModel) Load.model.name("Category", arg);

		if (categoryModel.update(category_id)) {
			Http.redirect("/main/done");
		} else {
			super.request.setAttribute("Data", categoryModel);
			Load.view.name("/category/save.jsp");
		}

	}

	public void list() {
		CategoryModel categoryModel = (CategoryModel) Load.model.name("Category");
		categoryModel.findAll();
		Load.view.name("/category/list.jsp", categoryModel);
	}

	public void del(String category_id) throws IOException {
		CategoryModel categoryModel = (CategoryModel) Load.model.name("Category");
		String str = categoryModel.del(Integer.parseInt(category_id));
		Load.view.out(str);
	}

	

}
