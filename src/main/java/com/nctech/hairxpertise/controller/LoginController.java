package com.nctech.hairxpertise.controller;


import com.nctech.hairxpertise.dtos.Login;
import com.nctech.hairxpertise.repository.LoginRepository;
import com.nctech.hairxpertise.repository.impl.LoginRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hairxpertise/login/")
public class LoginController {

//    private final LoginRepository loginRepository;
//
//    @Autowired
//    public LoginController(LoginRepository loginRepository) {
//        this.loginRepository = loginRepository;
//    }

    LoginRepository loginRepository = new LoginRepositoryImpl();

    @PostMapping("save")
    public String createLogin(@RequestBody Login login) {

        return loginRepository.createLogin(login);
    }


}
