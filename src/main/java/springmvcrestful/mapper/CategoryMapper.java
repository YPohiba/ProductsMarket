package springmvcrestful.mapper;

import org.springframework.jdbc.core.RowMapper;
import springmvcrestful.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yevhen Pohiba on 11.12.2017.
 */
public class CategoryMapper implements RowMapper {
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Category(resultSet.getInt("id"), resultSet.getString("name"));
    }
}
