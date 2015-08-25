/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.loader;

import blog.system.tools.ErrorPage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author petroff
 */
public class Load {

	public static HttpServletRequest request;
	public static HttpServletResponse response;
	public static ErrorPage errorPage;

	public static Model model;
	public static View view;

	public static void init(HttpServletRequest request, HttpServletResponse response, ErrorPage errorPage) {
		Load.request = request;
		Load.response = response;
		Load.errorPage = errorPage;
		model = new Model();
		view = new View();
	}

}
