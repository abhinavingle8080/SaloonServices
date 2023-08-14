package com.nctech.hairxpertise.controller;


import com.nctech.hairxpertise.dtos.Salon;
import com.nctech.hairxpertise.services.SalonServices;
import com.nctech.hairxpertise.services.impl.SalonServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hairxpertise/salon")
public class SalonController {

//    public final SalonServices salonServices;
//
//    @Autowired
//    public SalonController(SalonServices salonServices){
//        this.salonServices = salonServices;
//    }

    SalonServices salonServices = new SalonServicesImpl();

    @PostMapping("/save")
    public void save(@RequestBody Salon salon) {
            salon.setId(salonServices.fetchAll().size() +1);
            salonServices.save(salon);
    }

    @GetMapping("/fetchAll")
    public List<Salon> fetchAll() {
        return salonServices.fetchAll();
    }

    @GetMapping("/fetchByID/{id}")
    public Salon fetchByID(@PathVariable Integer id) {
        return salonServices.fetchByID(id);
    }

    @PostMapping("/update")
    public void update(@RequestBody Salon salon) {
        salonServices.update(salon);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestBody Integer id) {
        return salonServices.delete(id);
    }
}


