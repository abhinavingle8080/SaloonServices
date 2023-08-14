package com.nctech.hairxpertise.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing user login credentials.
 * This class is used to map user login information to a database table.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;
    private String password;

}
