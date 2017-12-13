package springmvcrestful.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springmvcrestful.dao.CategoryDAO;
import springmvcrestful.mapper.CategoryMapper;
import springmvcrestful.model.Category;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Yevhen Pohiba on 11.12.2017.
 */
@Repository
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    @Override
    public void init(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addCategory(String name) {
        String query = "INSERT INTO categories (name) VALUES (?)";
        jdbcTemplate.update(query, name);
        System.out.println("Category successfully created - " + name);
    }

    @Override
    public void updateCategory(int id, String name) {
        String query = "UPDATE categories SET name = ? WHERE id = ?";
        jdbcTemplate.update(query, name, id);
        System.out.println("Category successfully updated, with id - " + id + " ("+name+")");
    }

    @Override
    public void deleteCategory(int id) {
        String query = "DELETE FROM categories WHERE id = ?";
        jdbcTemplate.update(query, id);
        System.out.println("Category successfully removed, with id - " + id);
    }

    @Override
    public Category getCategoryById(int id) {
        String query = "SELECT * FROM categories WHERE id = ?";
        return (Category) jdbcTemplate.queryForObject(query, new Object[]{id}, new CategoryMapper());
    }

    @Override
    public List getListCategories() {
        String query = "SELECT * FROM categories ORDER BY name";
        return jdbcTemplate.query(query, new CategoryMapper());
    }
}
