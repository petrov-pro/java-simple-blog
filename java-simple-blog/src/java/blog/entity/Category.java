/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.entity;

import blog.system.annotation.Bind;
import blog.validation.annotation.Unique;
import javax.validation.constraints.NotNull;

/**
 *
 * @author petroff
 */
public class Category {

	private static String errorMessage = "";

	public int id;

	@Bind
	@NotNull
	public Boolean enable;

	@Bind
	@NotNull
	@Unique(model_name = "Category")
	public String alias;

	@Bind
	@NotNull
	public Integer weight;

	@Bind
	@NotNull
	public Integer title_id;

	public static String getErrorMessage() {
		return errorMessage;
	}

	public static void setErrorMessage(String errorMessage) {
		Category.errorMessage = errorMessage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getTitle_id() {
		return title_id;
	}

	public void setTitle_id(Integer title_id) {
		this.title_id = title_id;
	}

}
