package springmvcrestful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springmvcrestful.dao.CategoryDAO;
import springmvcrestful.model.Category;

import java.util.List;

/**
 * Created by Yevhen Pohiba on 11.12.2017.
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryDAO categoryDAO;

    @ModelAttribute("category")
    public Category newCategory() {
        return new Category();
    }

}
