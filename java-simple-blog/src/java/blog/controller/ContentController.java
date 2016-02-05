/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.model.ContentModel;
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
public class ContentController extends ControllerImpl<ContentController> {

	@Override
	public void index() throws ServletException, IOException {
		Http.redirect("/main/");
	}

	@Get
	public void update(blog.system.environment.Get get, String content_id) throws ServletException, IOException {
		ContentModel contentModel = (ContentModel) Load.model.name("Content");
		contentModel.findByPk(Integer.parseInt(content_id));
		contentModel.navigator.setViewProfile("/navigator/user.jsp", "content_update");
		Load.view.name("/content/save.jsp", contentModel);
	}

	@Post
	public void update(blog.system.environment.Post post, String content_id) throws ServletException, IOException {
		ContentModel contentModel = (ContentModel) Load.model.name("Content");
		contentModel.navigator.setViewProfile("/navigator/user.jsp", "content_update");
		if (contentModel.update(content_id)) {
			Http.redirect("/main/done");
		} else {
			super.request.setAttribute("Data", contentModel);
			Load.view.name("/content/save.jsp");
		}

	}

	public void list() {
		ContentModel contentModel = (ContentModel) Load.model.name("Content");
		contentModel.findAll();
		contentModel.navigator.setViewProfile("/navigator/user.jsp", "content_list");
		Load.view.name("/content/list.jsp", contentModel);
	}

}
