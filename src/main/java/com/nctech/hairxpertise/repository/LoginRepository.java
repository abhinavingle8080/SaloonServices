package com.nctech.hairxpertise.repository;

import com.nctech.hairxpertise.dtos.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository  {

    public String createLogin(Login login);

//    // Custom query method to find a login entity by username
//    Optional<LoginEntity> findByUserName(String userName);
//
//    // Custom query method to find login entities by password
//    List<LoginEntity> findByPassword(String password);
//
//    // Add more custom query methods if needed
}
