package com.jgeekmz.management_app.controllers;

import com.jgeekmz.management_app.models.VehicleHire;
import com.jgeekmz.management_app.services.ClientService;
import com.jgeekmz.management_app.services.LocationService;
import com.jgeekmz.management_app.services.VehicleHireService;
import com.jgeekmz.management_app.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class VehicleHireController {
    @Autowired
    private VehicleHireService vehicleHireService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private VehicleService vehicleService;

    //Get All VehicleHires
    @GetMapping("vehicleHires")
    public String findAll(Model model) {
        model.addAttribute("vehicleHires", vehicleHireService.findAll());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("vehicles", vehicleService.findAll());
        return "vehicleHire";
    }

    @RequestMapping("vehicleHires/findById")
    @ResponseBody
    public Optional<VehicleHire> findById(Integer id) {
        return vehicleHireService.findById(id);
    }

    //Add VehicleHire
    @PostMapping(value = "vehicleHires/addNew")
    public String addNew(VehicleHire vehicleHire) {
        vehicleHireService.save(vehicleHire);
        return "redirect:/vehicleHires";
    }

    @RequestMapping(value = "vehicleHires/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(VehicleHire vehicleHire) {
        vehicleHireService.save(vehicleHire);
        return "redirect:/vehicleHires";
    }

    @RequestMapping(value = "vehicleHires/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Integer id) {
        vehicleHireService.delete(id);
        return "redirect:/vehicleHires";
    }
}