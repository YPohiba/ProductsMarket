package springmvcrestful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springmvcrestful.dao.ProductDAO;
import springmvcrestful.model.Product;

/**
 * Created by Yevhen Pohiba on 11.12.2017.
 */
@Controller
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @ModelAttribute("product")
    public Product newProduct() {
        return new Product();
    }

}
