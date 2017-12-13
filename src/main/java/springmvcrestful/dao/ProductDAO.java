package springmvcrestful.dao;

import springmvcrestful.model.Product;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Yevhen Pohiba on 10.12.2017.
 */
public interface ProductDAO {

    public void init();

    public void addProduct(String name, String description, float price, boolean instock, int idManufacturer, int idCategory);

    public void updateProduct(int id, String name, String description, float price, boolean instock, int idManufacturer, int idCategory);

    public void deleteProduct(int id);

    public Product getProductById(int id);

    public List getListProducts();

    public List getListProductsWithFilter(Product product, float minPrice, float maxPrice);
}
