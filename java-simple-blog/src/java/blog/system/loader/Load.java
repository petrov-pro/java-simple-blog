/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.loader;

import blog.system.tools.ErrorPage;
import java.util.ResourceBundle;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
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
	public static ResourceBundle bundle;

	public static ResourceBundle getBundle(HttpServletRequest request) {
		return bundle;
	}

	public static void setBundle(ResourceBundle bundle) {
		Load.bundle = bundle;
	}

	public Load(HttpServletRequest request, HttpServletResponse response, ErrorPage errorPage) {
		Load.request = request;
		Load.response = response;
		Load.errorPage = errorPage;
		model = new Model();
		view = new View();
		Bundle bundle = new Bundle();
		Load.bundle = bundle.getBundle(request);
	}



}
