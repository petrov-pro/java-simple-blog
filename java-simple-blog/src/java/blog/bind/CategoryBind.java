/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.bind;

import blog.entity.Category;
import blog.system.loader.Load;
import blog.system.tools.BindParams;
import java.util.Map;

/**
 *
 * @author petroff
 */
public class CategoryBind {

    public static Category bind(Category category) {
        return parseForm(category);
    }

    public static Category bind(Category category, String category_id) {
        category.setId(Integer.parseInt(category_id));
        return parseForm(category);
    }

    private static Category parseForm(Category category) {
        Map<String, String> mp = BindParams.getParameterMap(Load.request, "category_name");
        for (Map.Entry<String, String> entry : mp.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            category.translate.put(key, value);
        }
        BindParams.bind(category);
        return category;
    }

}
