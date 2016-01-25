/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.model.CategoryModel;
import blog.model.MainModel;
import blog.system.annotation.Get;
import blog.system.controller.ControllerImpl;
import blog.system.loader.Load;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import org.json.simple.JSONObject;

/**
 *
 * @author petroff
 */
public class MainController extends ControllerImpl<MainController> {

	@Override
	public void index() {
		MainModel mainModel = (MainModel) Load.model.name("Main");
		mainModel.getCategoryArticle();
                mainModel.navigator.setViewMain("/navigator/main.jsp", "main");
		Load.view.name("/main/main.jsp", mainModel);
	}

	@Get
	public void setlang(blog.system.environment.Get get, String lang) throws ServletException, IOException {
		String status;
		if (Arrays.asList(Load.config.langs).contains(lang)) {
			status = "ok";
			Load.request.getSession().setAttribute("lang", lang);

		} else {
			status = "error";
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("status", status);
		String str = resultJson.toString();
		Load.view.out(str);
	}

	public void done() {
		MainModel mainModel = null;
		if (Load.auth.isAuth()) {
			mainModel = (MainModel) Load.model.name("Main");
			mainModel.navigator.setViewProfile("/navigator/user.jsp", "");
		}
		Load.view.name("/main/done.jsp", mainModel);
	}

	@Get
	public void category(blog.system.environment.Get get, String user_id, String category_alias) throws ServletException, IOException {
		MainModel mainModel = (MainModel) Load.model.name("Main");
		mainModel.getCategory(user_id, category_alias);
		mainModel.navigator.setViewMain("/navigator/main.jsp", "main_category");
		Load.view.name("/main/category.jsp", mainModel);
	}

	@Get
	public void article(blog.system.environment.Get get, String user_id, String article_alias) throws ServletException, IOException {
		MainModel mainModel = (MainModel) Load.model.name("Main");
		mainModel.getCategoryArticle(user_id, article_alias);
		mainModel.navigator.setViewMain("/navigator/main.jsp", "main_article");
		Load.view.name("/main/article.jsp", mainModel);
	}

	@Get
	public void tag(blog.system.environment.Get get, String tag) throws ServletException, IOException {
		MainModel mainModel = (MainModel) Load.model.name("Main");
		mainModel.getArticleForTag(tag);
		mainModel.navigator.setViewMain("/navigator/main.jsp", "main_tag");
		Load.view.name("/main/tag.jsp", mainModel);
	}

	@Get
	public void user(blog.system.environment.Get get, String userName) throws ServletException, IOException {
		MainModel mainModel = (MainModel) Load.model.name("Main");
		mainModel.getCategoryArticleForUser(userName);
		mainModel.navigator.setViewMain("/navigator/main.jsp", "main_user");
		Load.view.name("/main/user.jsp", mainModel);
	}

}
