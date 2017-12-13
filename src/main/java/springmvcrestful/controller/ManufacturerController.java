package springmvcrestful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springmvcrestful.dao.ManufacturerDAO;
import springmvcrestful.model.Manufacturer;

/**
 * Created by Yevhen Pohiba on 11.12.2017.
 */
@Controller
public class ManufacturerController {

    @Autowired
    private ManufacturerDAO manufacturerDAO;

    @ModelAttribute("manufacturer")
    public Manufacturer newProduct() {
        return new Manufacturer();
    }

}
