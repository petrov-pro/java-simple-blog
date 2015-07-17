/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.system.ControllerImpl;
import blog.system.exception.Exception404;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author petroff
 */
public class ArticleController extends ControllerImpl<ArticleController> {

	public void test() {
		super.getView("/article/article.jsp");
	}

	@Override
	public ArticleController index() {
		super.getView("/article/article.jsp");
		return this;
	}
}
