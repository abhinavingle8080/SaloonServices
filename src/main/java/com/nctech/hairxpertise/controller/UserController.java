package com.nctech.hairxpertise.controller;


import com.nctech.hairxpertise.dtos.User;
import com.nctech.hairxpertise.services.UserServices;
import com.nctech.hairxpertise.services.impl.UserServicesImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hairxpertise/user/")
public class UserController {

//    public final UserServices userServices;
//
//    @Autowired
//    public UserController(UserServices userServices){
//        this.userServices = userServices;
//    }

    UserServices userServices = new UserServicesImpl();

    @PostMapping("save")
    public void save(@RequestBody User user) {
        user.setId(userServices.fetchAll().size() + 1);
        userServices.save(user);
    }

    @GetMapping("fetchAll")
    public List<User> fetchAll() {
        return userServices.fetchAll();
    }

    @GetMapping("fetchByID/{id}")
    public User fetchByID(@PathVariable Integer id) {
        return userServices.fetchByID(id);
    }

    @PostMapping("update")
    public void update(@RequestBody User user) {
        userServices.update(user);
    }

    @PostMapping("delete")
    public boolean delete(@RequestBody Integer id) {
        return userServices.delete(id);
    }
}


