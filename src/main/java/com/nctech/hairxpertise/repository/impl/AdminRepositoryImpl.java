package com.nctech.hairxpertise.repository.impl;

import com.nctech.hairxpertise.dtos.Admin;
import com.nctech.hairxpertise.dtos.Admin;
import com.nctech.hairxpertise.repository.AdminRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepositoryImpl implements AdminRepository {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:h2:file:./stddb";
        String username = "sa";
        String password = "sa";
        return DriverManager.getConnection(url, username, password);
    }


    /**
     * @param admin 
     */
    @Override
    public void save(Admin admin) {
        try (Connection connection = getConnection()){
            String sql = "INSERT INTO Admins VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, admin.getId());
            statement.setString(2, admin.getUserName());
            statement.setString(3, admin.getPassword());
            statement.setString(4, admin.getFullName());
            statement.setString(5, admin.getGender());
            statement.setInt(6, admin.getAge());
            statement.setString(7, admin.getContact());
            statement.setString(8, admin.getEmail());
            statement.setString(9, admin.getAddress());

            statement.executeUpdate();
            System.out.println("Saved successfully");
        }catch (SQLException e){
            System.out.println("Save Failed");
        }
    }

    /**
     * @return 
     */
    @Override
    public List<Admin> get() {
        List<Admin> users = new ArrayList<>();

        try (Connection connection = getConnection()) {

            String sql = "SELECT id, userName, password, fullName, gender, age, contact, email, address FROM Admins";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String userName = resultSet.getString ("userName");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("fullName");
                String gender = resultSet.getString("gender");
                Integer age = resultSet.getInt("age");
                String contact = resultSet.getString("contact");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                Admin admin = new Admin(id, userName, password, fullName, gender, age, contact, email,address);
                users.add(admin);
            }

        }catch(SQLException e){
            System.out.println("Fetching failed!!!");
        }
        return users;
    }
}
