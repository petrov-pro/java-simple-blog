/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.loader;

/**
 *
 * @author petroff
 */
public class View {

	public static final String basePath = "/WEB-INF/views/";

	public void name(String path) {
		try {
			Load.response.setContentType("text/html;charset=UTF-8");
			Load.request.getRequestDispatcher(basePath + path).forward(Load.request, Load.response);
		} catch (Exception e) {
			Load.errorPage.show404(e);
		}
	}

}
