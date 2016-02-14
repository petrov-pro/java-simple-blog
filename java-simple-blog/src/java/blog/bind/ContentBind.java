/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.bind;

import blog.entity.Content;
import blog.system.tools.BindParams;

/**
 *
 * @author petroff
 */
public class ContentBind {

	public static Content bind(Content content) {
		BindParams.bind(content);
		return content;
	}
}
