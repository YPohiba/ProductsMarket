package springmvcrestful.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springmvcrestful.dao.ProductDAO;
import springmvcrestful.mapper.ProductMapper;
import springmvcrestful.model.Product;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Yevhen Pohiba on 10.12.2017.
 */
@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    @Override
    public void init(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addProduct(String name, String description, float price, boolean instock, int idManufacturer, int idCategory) {
        String query = "INSERT INTO products (name, price, description, instock, id_manufacturer, id_category) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(query, name, price, description, instock, idManufacturer, idCategory);
        System.out.println("Product successfully created - " + name);
    }

    @Override
    public void updateProduct(int id, String name, String description, float price, boolean instock, int idManufacturer, int idCategory) {
        String query = "UPDATE products SET name = ?, description = ?, price = ?, instock = ?, id_manufacturer = ?, id_category = ? WHERE id = ?";
        jdbcTemplate.update(query, name, description, price, instock, idManufacturer, idCategory, id);
        System.out.println("Product successfully updated, with id - " + id + " ("+name+")");
    }

    @Override
    public void deleteProduct(int id) {
        String query = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(query, id);
        System.out.println("Product successfully removed, with id - " + id);
    }

    @Override
    public Product getProductById(int id) {
        String query = "SELECT * FROM products WHERE id = ?";
        return (Product) jdbcTemplate.queryForObject(query, new Object[]{id}, new ProductMapper());
    }

    @Override
    public List getListProducts() {
        String query = "SELECT * FROM products ORDER BY name";
        return jdbcTemplate.query(query, new ProductMapper());
    }

    @Override
    public List getListProductsWithFilter(Product product, float minPrice, float maxPrice){
        String params = "";
        if (product.getIdCategory() != 0){
            params = " AND id_category = " + product.getIdCategory();
        }
        if (product.getIdManufacturer() != 0){
            params += " AND id_manufacturer = " + product.getIdManufacturer();
        }
        if (product.isInstock()){
            params += " AND instock = " + product.isInstock();
        }
        String query = "SELECT * FROM products WHERE price >= ? AND price <= ? "+params;
        return jdbcTemplate.query(query, new Object[]{minPrice, maxPrice}, new ProductMapper());
    }
}
