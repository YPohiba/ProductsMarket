package springmvcrestful.dao;

import springmvcrestful.model.Manufacturer;

import java.util.List;

/**
 * Created by Yevhen Pohiba on 10.12.2017.
 */
public interface ManufacturerDAO {

    public void init();

    public void addManufacturer(String name);

    public void updateManufacturer(int id, String name);

    public void deleteManufacturer(int id);

    public Manufacturer getManufacturerById(int id);

    public List getListManufacturers();

    public List getListManufacturersByIdCategory(int id);
}
