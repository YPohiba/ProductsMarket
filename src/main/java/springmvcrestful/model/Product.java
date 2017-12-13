package springmvcrestful.model;

/**
 * Created by Yevhen Pohiba on 10.12.2017.
 */
public class Product {

    private int id, idManufacturer, idCategory;
    private String name, description;
    private float price;
    private boolean instock;

    public Product(int id, int idManufacturer, int idCategory, String name, String description, float price, boolean instock) {
        this.id = id;
        this.idManufacturer = idManufacturer;
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
        this.price = price;
        this.instock = instock;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isInstock() {
        return instock;
    }

    public void setInstock(boolean instock) {
        this.instock = instock;
    }

    public int getIdManufacturer() {
        return idManufacturer;
    }

    public void setIdManufacturer(int idManufacturer) {
        this.idManufacturer = idManufacturer;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
