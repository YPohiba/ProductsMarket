package springmvcrestful.mapper;

import org.springframework.jdbc.core.RowMapper;
import springmvcrestful.model.Manufacturer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yevhen Pohiba on 10.12.2017.
 */
public class ManufacturerMapper implements RowMapper {
    @Override
    public Manufacturer mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Manufacturer(resultSet.getInt("id"), resultSet.getString("name"));
    }
}
