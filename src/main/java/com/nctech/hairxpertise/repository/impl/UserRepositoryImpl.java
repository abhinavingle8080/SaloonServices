package com.nctech.hairxpertise.repository.impl;

import com.nctech.hairxpertise.dtos.User;
import com.nctech.hairxpertise.dtos.User;
import com.nctech.hairxpertise.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:h2:file:./stddb";
        String username = "sa";
        String password = "sa";
        return DriverManager.getConnection(url, username, password);
    }
    
    /**
     * @param user 
     */
    @Override
    public void save(User user) {
        try (Connection connection = getConnection()){
            String sql = "INSERT INTO Users VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFullName());
            statement.setString(5, user.getGender());
            statement.setInt(6, user.getAge());
            statement.setString(7, user.getContact());
            statement.setString(8, user.getEmail());
            statement.setString(9, user.getAddress());

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
    public List<User> get() {
        List<User> users = new ArrayList<>();

        try (Connection connection = getConnection()) {

            String sql = "SELECT id, userName, password, fullName, gender, age, contact, email, address FROM Users";
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

                User user = new User(id, userName, password, fullName, gender, age, contact, email,address);
                users.add(user);
            }

        }catch(SQLException e){
            System.out.println("Fetching failed!!!");
        }
        return users;
    }
}
