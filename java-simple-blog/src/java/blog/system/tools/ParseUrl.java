/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.tools;

/**
 *
 * @author petroff
 */
public class ParseUrl {

	private String classUrl = "Main";
	private String methodUrl = null;
	private Object[] paramsUrl;
	private Class[] paramTypesUrl;

	public ParseUrl(String url) {
		if (url.contains("/")) {
			String[] parts = null;
			parts = url.split("/");
			int length = parts.length;
			if (length > 1 && parts[1] != null) {
				classUrl = Url.normalizeUrl(parts[1]);
			}

			if (length > 2 && parts[2] != null) {
				String methodUrlRaw = parts[2];
				methodUrl = methodUrlRaw.toLowerCase();
			}

			if (length > 3) {
				paramsUrl = new Object[length - 3];
				paramTypesUrl = new Class[length - 3];
				int ii = 0;
				for (int i = 3; i < length; i++) {
					paramsUrl[ii] = parts[i];
					paramTypesUrl[ii] = String.class;
					ii++;
				}
			}


		}
	}

	public Class[] getParamTypesUrl() {
		return paramTypesUrl;
	}

	public void setParamTypesUrl(Class[] paramTypesUrl) {
		this.paramTypesUrl = paramTypesUrl;
	}

	public String getClassUrl() {
		return classUrl;
	}

	public void setClassUrl(String classUrl) {
		this.classUrl = classUrl;
	}

	public String getMethodUrl() {
		return methodUrl;
	}

	public void setMethodUrl(String methodUrl) {
		this.methodUrl = methodUrl;
	}

	public Object[] getParamsUrl() {
		return paramsUrl;
	}

	public void setParamsUrl(Object[] paramsUrl) {
		this.paramsUrl = paramsUrl;
	}
}
