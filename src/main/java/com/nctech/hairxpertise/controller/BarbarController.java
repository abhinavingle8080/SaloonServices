package com.nctech.hairxpertise.controller;

import com.nctech.hairxpertise.dtos.Barbar;
import com.nctech.hairxpertise.services.BarbarServices;
import com.nctech.hairxpertise.services.impl.BarbarServicesImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hairxpertise/barbar/")
public class BarbarController {

//    public final BarbarServices barbarServices;
//
//    @Autowired
//    public UserController(BarbarServices barbarServices){
//        this.barbarServices = barbarServices;
//    }

    BarbarServices barbarServices = new BarbarServicesImpl();

    @PostMapping("save")
    public void save(@RequestBody Barbar barbar) {
        barbar.setId(barbarServices.fetchAll().size() + 1);
        barbarServices.save(barbar);
    }

    @GetMapping("fetchAll")
    public List<Barbar> fetchAll() {
        return barbarServices.fetchAll();
    }

    @GetMapping("fetchByID/{id}")
    public Barbar fetchByID(@PathVariable Integer id) {
        return barbarServices.fetchByID(id);
    }

    @PostMapping("update")
    public void update(@RequestBody Barbar barbar) {
        barbarServices.update(barbar);
    }

    @PostMapping("delete")
    public boolean delete(@RequestBody Integer id) {
        return barbarServices.delete(id);
    }
}
