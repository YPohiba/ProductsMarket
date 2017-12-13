package springmvcrestful.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springmvcrestful.dao.ManufacturerDAO;
import springmvcrestful.model.Manufacturer;

import java.util.List;

/**
 * Created by Yevhen Pohiba on 11.12.2017.
 */
@RestController
public class ManufacturerRESTController {

    @Autowired
    private ManufacturerDAO manufacturerDAO;

    // http://localhost:8080/getManufs
    @RequestMapping(value = "/getManufs",
            method =  RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            headers={"content-type=application/xml", "content-type=application/json"})
    @ResponseBody
    public List getAllManufacturers(){
        return manufacturerDAO.getListManufacturers();
    }

    @RequestMapping(value = "/getManuf/{id}",
            method =  RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            headers={"content-type=application/xml", "content-type=application/json"})
    @ResponseBody
    public Manufacturer getManufacturer(@PathVariable("id") int id){
        return manufacturerDAO.getManufacturerById(id);
    }

    @RequestMapping(value = "/addManuf",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            headers={"content-type=application/xml", "content-type=application/json"})
    @ResponseBody
    public Manufacturer addManufacturer(@RequestBody Manufacturer manufacturer){
        manufacturerDAO.addManufacturer(manufacturer.getName());
        return manufacturer;
    }

    @RequestMapping(value = "/updateManuf",
            method = RequestMethod.PUT,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            headers={"content-type=application/xml", "content-type=application/json"})
    @ResponseBody
    public Manufacturer updateManufacturer(@RequestBody Manufacturer manufacturer){
        manufacturerDAO.updateManufacturer(manufacturer.getId(), manufacturer.getName());
        return manufacturer;
    }

    @RequestMapping(value = "/deleteManuf/{id}",
            method = RequestMethod.DELETE,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            headers={"content-type=application/xml", "content-type=application/json"})
    @ResponseBody
    public void deleteManufacturer(@PathVariable("id") int id){
        manufacturerDAO.deleteManufacturer(id);
    }
}
