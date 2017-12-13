package springmvcrestful.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springmvcrestful.dao.ManufacturerDAO;
import springmvcrestful.mapper.ManufacturerMapper;
import springmvcrestful.model.Manufacturer;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Yevhen Pohiba on 10.12.2017.
 */
@Repository
public class ManufacturerDAOImpl implements ManufacturerDAO{

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    @Override
    public void init(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addManufacturer(String name) {
        String query = "INSERT INTO manufacturers (name) VALUES (?)";
        jdbcTemplate.update(query, name);
        System.out.println("Manufacturer successfully created - " + name);
    }

    @Override
    public void updateManufacturer(int id, String name) {
        String query = "UPDATE manufacturers SET name = ? WHERE id = ?";
        jdbcTemplate.update(query, name, id);
        System.out.println("Manufacturer successfully updated, with id - " + id + " ("+name+")");
    }

    @Override
    public void deleteManufacturer(int id) {
        String query = "DELETE FROM manufacturers WHERE id = ?";
        jdbcTemplate.update(query, id);
        System.out.println("Manufacturer successfully removed, with id - " + id);
    }

    @Override
    public Manufacturer getManufacturerById(int id) {
        String query = "SELECT * FROM manufacturers WHERE id = ?";
        return (Manufacturer) jdbcTemplate.queryForObject(query, new Object[]{id}, new ManufacturerMapper());
    }

    @Override
    public List getListManufacturers() {
        String query = "SELECT * FROM manufacturers ORDER BY name";
        return jdbcTemplate.query(query, new ManufacturerMapper());
    }

    @Override
    public List getListManufacturersByIdCategory(int id) {
        String query = "SELECT * FROM manufacturers WHERE manufacturers.id IN (SELECT id_manufacturer FROM products WHERE id_category = ?)";
        return jdbcTemplate.query(query, new Object[]{id}, new ManufacturerMapper());
    }
}
