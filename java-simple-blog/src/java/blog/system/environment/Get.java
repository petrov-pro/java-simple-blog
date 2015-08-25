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
public class Get extends GetPostImpl {

	public Get(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getJson() {
		return "";
	}

}
