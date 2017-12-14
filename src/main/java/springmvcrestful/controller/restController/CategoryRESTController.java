package springmvcrestful.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springmvcrestful.dao.CategoryDAO;
import springmvcrestful.model.Category;

import java.util.List;

/**
 * Created by Yevhen Pohiba on 11.12.2017.
 */
@RestController
public class CategoryRESTController {

    @Autowired
    private CategoryDAO categoryDAO;

    @RequestMapping(value = "/ProductsMarket/getCategories",
            method =  RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            headers={"content-type=application/xml", "content-type=application/json"})
    @ResponseBody
    public List getAllCategories(){
        return categoryDAO.getListCategories();
    }

    @RequestMapping(value = "/ProductsMarket/getCategory/{id}",
            method =  RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            headers={"content-type=application/xml", "content-type=application/json"})
    @ResponseBody
    public Category getCategory(@PathVariable("id") int id){
        return categoryDAO.getCategoryById(id);
    }

    @RequestMapping(value = "/ProductsMarket/addCategory",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            headers={"content-type=application/xml", "content-type=application/json"})
    @ResponseBody
    public Category addCategory(@RequestBody Category category){
        categoryDAO.addCategory(category.getName());
        return category;
    }

    @RequestMapping(value = "/ProductsMarket/updateCategory",
            method = RequestMethod.PUT,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            headers={"content-type=application/xml", "content-type=application/json"})
    @ResponseBody
    public Category updateCategory(@RequestBody Category category){
        categoryDAO.updateCategory(category.getId(), category.getName());
        return category;
    }

    @RequestMapping(value = "/ProductsMarket/deleteCategory/{id}",
            method = RequestMethod.DELETE,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            headers={"content-type=application/xml", "content-type=application/json"})
    @ResponseBody
    public void deleteCategory(@PathVariable("id") int id){
        categoryDAO.deleteCategory(id);
    }
}
