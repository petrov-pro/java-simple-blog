/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.environment;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author petroff
 */
abstract class GetPostImpl implements GetPostIntf {

	private HttpServletRequest request;

	public GetPostImpl(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String getParameter(String s) {
		return request.getParameter(s);
	}

}
