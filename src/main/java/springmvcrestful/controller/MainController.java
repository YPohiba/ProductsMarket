package springmvcrestful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springmvcrestful.dao.CategoryDAO;
import springmvcrestful.dao.ManufacturerDAO;
import springmvcrestful.dao.ProductDAO;
import springmvcrestful.model.Category;
import springmvcrestful.model.Manufacturer;
import springmvcrestful.model.Product;

/**
 * Created by Yevhen Pohiba on 09.12.2017.
 */
@Controller
public class MainController {

    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private ManufacturerDAO manufacturerDAO;

    private void uploadDefaultAttributes(ModelMap modelMap){
        modelMap.addAttribute("minPrice", 0.0);
        modelMap.addAttribute("maxPrice", 99999.0);
        modelMap.addAttribute("instock", false);
        modelMap.addAttribute("filterIdCategory", 0);
        modelMap.addAttribute("filterIdManufacturer", 0);
        modelMap.addAttribute("categoriesList", categoryDAO.getListCategories());
        modelMap.addAttribute("manufacturerList", manufacturerDAO.getListManufacturers());
        modelMap.addAttribute("productsList", productDAO.getListProducts());
    }

    @RequestMapping(value = "/", method =  RequestMethod.GET)
    public String root(ModelMap modelMap){
        uploadDefaultAttributes(modelMap);
        return "index";
    }

    @RequestMapping(value = "/ProductsMarket/getFilteredProducts", method = RequestMethod.GET)
    public String getFilteredProducts (ModelMap modelMap,
                        @ModelAttribute("product") Product product,
                        @ModelAttribute("minPrice") float minPrice,
                        @ModelAttribute("maxPrice") float maxPrice){
        modelMap.addAttribute("minPrice", minPrice);
        modelMap.addAttribute("maxPrice", maxPrice);
        modelMap.addAttribute("instock", product.isInstock());
        modelMap.addAttribute("filterIdCategory", product.getIdCategory());
        modelMap.addAttribute("filterIdManufacturer", product.getIdManufacturer());
        modelMap.addAttribute("categoriesList", categoryDAO.getListCategories());
        modelMap.addAttribute("manufacturerList", manufacturerDAO.getListManufacturers());
        modelMap.addAttribute("productsList", productDAO.getListProductsWithFilter(product, minPrice, maxPrice));
        return "index";
    }

    @RequestMapping(value = "/ProductsMarket/newProduct", method = RequestMethod.POST)
    public String addNewProduct(@ModelAttribute("product") Product product, ModelMap modelMap){
        productDAO.addProduct(product.getName(), product.getDescription(), product.getPrice(), product.isInstock(), product.getIdManufacturer(), product.getIdCategory());
        uploadDefaultAttributes(modelMap);
        return "index";
    }

    @RequestMapping(value = "/ProductsMarket/newCategory", method = RequestMethod.POST)
    public String addNewCategory(@ModelAttribute("category") Category category, ModelMap modelMap){
        categoryDAO.addCategory(category.getName());
        uploadDefaultAttributes(modelMap);
        return "index";
    }

    @RequestMapping(value = "/ProductsMarket/newManufacturer", method = RequestMethod.POST)
    public String addNewManufacturer(@ModelAttribute("Manufacturer") Manufacturer manufacturer, ModelMap modelMap){
        manufacturerDAO.addManufacturer(manufacturer.getName());
        uploadDefaultAttributes(modelMap);
        return "index";
    }

    @RequestMapping(value = "/ProductsMarket/editProduct/{id}", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("product") Product product, @PathVariable("id") int id, ModelMap modelMap){
        product.setId(id);
        productDAO.updateProduct(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.isInstock(), product.getIdManufacturer(), product.getIdCategory());
        uploadDefaultAttributes(modelMap);
        return "index";
    }

    @RequestMapping(value = "/ProductsMarket/removeProduct/{id}", method = RequestMethod.POST)
    public String deleteProduct(@PathVariable("id") int id, ModelMap modelMap){
        productDAO.deleteProduct(id);
        uploadDefaultAttributes(modelMap);
        return "index";
    }

    @RequestMapping(value = "/ProductsMarket/changeProduct/{id}", method = RequestMethod.POST)
    public String changeProduct(@ModelAttribute("product") Product product,
                                @ModelAttribute("idButton")Object button,
                                @PathVariable("id") int id, ModelMap modelMap){
        if (button.equals("Delete")){
            productDAO.deleteProduct(id);
        } else if (button.equals("Update")){
            product.setId(id);
            productDAO.updateProduct(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.isInstock(), product.getIdManufacturer(), product.getIdCategory());
        }
        uploadDefaultAttributes(modelMap);
        return "index";
    }
}
