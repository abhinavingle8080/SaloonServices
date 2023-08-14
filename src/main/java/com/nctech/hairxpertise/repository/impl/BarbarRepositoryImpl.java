package com.nctech.hairxpertise.repository.impl;

import com.nctech.hairxpertise.dtos.Barbar;
import com.nctech.hairxpertise.repository.BarbarRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarbarRepositoryImpl implements BarbarRepository {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:h2:file:./stddb";
        String username = "sa";
        String password = "sa";
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * @param barbar
     */
    @Override
    public void save(Barbar barbar) {
        try (Connection connection = getConnection()){
            String sql = "INSERT INTO Barbars VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, barbar.getId());
            statement.setString(2, barbar.getUserName());
            statement.setString(3, barbar.getPassword());
            statement.setString(4, barbar.getFullName());
            statement.setString(5, barbar.getGender());
            statement.setInt(6, barbar.getAge());
            statement.setString(7, barbar.getContact());
            statement.setString(8, barbar.getEmail());
            statement.setString(9, barbar.getAddress());
            statement.setString(10,barbar.getExpertise());
            statement.setString(11, barbar.getWorkPlace());

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
    public List<Barbar> get() {
        List<Barbar> users = new ArrayList<>();

        try (Connection connection = getConnection()) {

            String sql = "SELECT id, userName, password, fullName, gender, age, contact, email, address, expertise, workPlace FROM Barbars";
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
                String expertise = resultSet.getString("expertise");
                String workPlace = resultSet.getString("workPlace");

                Barbar barbar = new Barbar(id, userName, password, fullName, gender, age, contact, email,address, expertise, workPlace);
                users.add(barbar);
            }

        }catch(SQLException e){
            System.out.println("Fetching failed!!!");
        }
        return users;
    }
}
