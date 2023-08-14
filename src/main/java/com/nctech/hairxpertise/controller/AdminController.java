package com.nctech.hairxpertise.controller;


import com.nctech.hairxpertise.dtos.Admin;
import com.nctech.hairxpertise.services.AdminServices;
import com.nctech.hairxpertise.services.impl.AdminServicesImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hairxpertise/admin/")
public class AdminController {

//    public final AdminServices adminServices;
//
//    @Autowired
//    public UserController(AdminServices adminServices){
//        this.adminServices = adminServices;
//    }

    AdminServices adminServices = new AdminServicesImpl();

    @PostMapping("save")
    public void save(@RequestBody Admin admin) {
        admin.setId(adminServices.fetchAll().size() + 1);
        adminServices.save(admin);
    }

    @GetMapping("fetchAll")
    public List<Admin> fetchAll() {
        return adminServices.fetchAll();
    }

    @GetMapping("fetchByID/{id}")
    public Admin fetchByID(@PathVariable Integer id) {
        return adminServices.fetchByID(id);
    }

    @PostMapping("update")
    public void update(@RequestBody Admin admin) {
        adminServices.update(admin);
    }

    @PostMapping("delete")
    public boolean delete(@RequestBody Integer id) {
        return adminServices.delete(id);
    }
}