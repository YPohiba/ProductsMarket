package springmvcrestful.mapper;

import org.springframework.jdbc.core.RowMapper;
import springmvcrestful.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yevhen Pohiba on 10.12.2017.
 */
public class ProductMapper implements RowMapper {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setName(resultSet.getString("name"));
        product.setId(resultSet.getInt("id"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getFloat("price"));
        product.setInstock(resultSet.getBoolean("instock"));
        product.setIdCategory(resultSet.getInt("id_category"));
        product.setIdManufacturer(resultSet.getInt("id_manufacturer"));
        return product;
    }
}
