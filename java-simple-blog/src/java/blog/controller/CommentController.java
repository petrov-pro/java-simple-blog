/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.model.ArticleModel;
import blog.model.CategoryModel;
import blog.model.CommentModel;
import blog.system.annotation.Get;
import blog.system.annotation.Post;
import blog.system.controller.ControllerImpl;
import blog.system.loader.Load;
import blog.system.tools.Http;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;
import org.json.simple.JSONObject;

/**
 *
 * @author petroff
 */
public class CommentController extends ControllerImpl<CommentController> {

	@Override
	public void index() throws ServletException, IOException {
		Http.redirect();
	}

	@Post
	public void create(blog.system.environment.Post post) throws ServletException, IOException {
		CommentModel commentModel = (CommentModel) Load.model.name("Comment");
		boolean result = commentModel.create();
		JSONObject resultJson = new JSONObject();
		resultJson.put("status", result);
		resultJson.put("message", commentModel.getErrorMessage());
		Load.view.out(resultJson.toString());
	}

	public void list() throws ServletException, IOException {
		CommentModel commentModel = (CommentModel) Load.model.name("Comment");
		boolean result = commentModel.findAll(Load.request.getParameter("article_id"), Load.request.getParameter("page"), true);
		commentModel.count(Load.request.getParameter("article_id"));
		String listCommentsHtml = Load.view.partial("/comment/list.jsp", commentModel);
		JSONObject resultJson = new JSONObject();
		resultJson.put("status", result);
		resultJson.put("message", listCommentsHtml);
		Load.view.out(resultJson.toString());
	}

	@Get
	public void get(blog.system.environment.Get get, String articleId, String page) throws ServletException, IOException {
		CommentModel commentModel = (CommentModel) Load.model.name("Comment");

		boolean result = commentModel.findAll(articleId, page, false);
		commentModel.count(articleId);
		commentModel.setArticleId(articleId);
		commentModel.navigator.setViewProfile("/navigator/user.jsp", "comment_get");
		Load.view.name("/comment/get.jsp", commentModel);
	}

	@Post
	public void reload(blog.system.environment.Post post) throws ServletException, IOException {
		HttpSession session = Load.session.getSession();
		if (session != null) {
			session.removeAttribute(Captcha.NAME);
		}
	}

	@Post
	public void update(blog.system.environment.Post post) throws ServletException, IOException {

		CommentModel commentModel = (CommentModel) Load.model.name("Comment");
		Boolean message = false;
		JSONObject resultJson = new JSONObject();
		message = commentModel.update();
		resultJson.put("message", message);
		Load.view.out(resultJson.toString());

	}

}
