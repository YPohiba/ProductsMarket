package springmvcrestful.dao;

import springmvcrestful.model.Category;

import java.util.List;

/**
 * Created by Yevhen Pohiba on 11.12.2017.
 */
public interface CategoryDAO {

    public void init();

    public void addCategory(String name);

    public void updateCategory(int id, String name);

    public void deleteCategory(int id);

    public Category getCategoryById(int id);

    public List getListCategories();
}
