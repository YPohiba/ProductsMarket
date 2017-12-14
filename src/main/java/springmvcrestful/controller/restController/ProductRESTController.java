package springmvcrestful.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springmvcrestful.dao.ProductDAO;
import springmvcrestful.model.Product;

import java.util.List;

/**
 * Created by Yevhen Pohiba on 11.12.2017.
 */
@RestController
public class ProductRESTController {

    @Autowired
    private ProductDAO productDAO;

    // http://localhost:8080/getProducts
    @RequestMapping(value = "/getProducts",
            method =  RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            headers={"content-type=application/xml", "content-type=application/json"})
    @ResponseBody
    public List getAllProducts(){
        return productDAO.getListProducts();
    }

    @RequestMapping(value = "/getProduct/{id}",
            method =  RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            headers={"content-type=application/xml", "content-type=application/json"})
    @ResponseBody
    public Product getProduct(@PathVariable("id") int id){
        return productDAO.getProductById(id);
    }

    @RequestMapping(value = "/addProduct",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            headers={"content-type=application/xml", "content-type=application/json"})
    @ResponseBody
    public Product addProduct(@RequestBody Product product){
        productDAO.addProduct(product.getName(), product.getDescription(), product.getPrice(), product.isInstock(), product.getIdManufacturer(), product.getIdCategory());
        return product;
    }

    @RequestMapping(value = "/updateProduct",
            method = RequestMethod.PUT,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            headers={"content-type=application/xml", "content-type=application/json"})
    @ResponseBody
    public Product updateProduct(@RequestBody Product product){
        productDAO.updateProduct(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.isInstock(), product.getIdManufacturer(), product.getIdCategory());
        return product;
    }

    @RequestMapping(value = "/deleteProduct/{id}",
            method = RequestMethod.DELETE,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            headers={"content-type=application/xml", "content-type=application/json"})
    @ResponseBody
    public void deleteProduct(@PathVariable("id") int id){
        productDAO.deleteProduct(id);
    }
}
