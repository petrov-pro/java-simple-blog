/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

import blog.system.Model;
import blog.system.intf.ModelIntf;

/**
 *
 * @author petroff
 */
public class Article extends Model implements ModelIntf {

	public Article() {
	}

	public String getArticle() {
		return "Article";
	}

	@Override
	public String getView() {
		return "/article/article";
	}
	
	@Override
	public Article getData(){
		return this;
	}
}
