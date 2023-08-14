package com.nctech.hairxpertise.repository.impl;

import com.nctech.hairxpertise.dtos.Salon;
import com.nctech.hairxpertise.repository.SalonRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class SalonRepositoryImpl implements SalonRepository {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:h2:file:./stddb";
        String username = "sa";
        String password = "sa";
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * @param salon
     */
    @Override
    public void save(Salon salon) {
        try (Connection connection = getConnection()){
            String sql = "INSERT INTO Salons VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, salon.getId());
            statement.setString(2, salon.getSalonName());
            statement.setString(3, salon.getSalonOwner());
            statement.setString(4, salon.getSalonAddress());
            statement.setString(5, salon.getSalonContact());
            statement.setInt(6, salon.getNoOfEmp());
            statement.setString(7, salon.getSalonType());
            statement.setString(8, salon.getSalonDescription());

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
    public List<Salon> get() {
        List<Salon> salons = new ArrayList<>();

        try (Connection connection = getConnection()) {

            String sql = "SELECT id, salonName, salonOwner, salonAddress, salonContact, noOfEmp, salonType, salonDescription FROM Salons";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String salonName = resultSet.getString ("salonName");
                String salonOwner = resultSet.getString("salonOwner");
                String salonAddress = resultSet.getString("salonAddress");
                String salonContact = resultSet.getString("salonContact");
                Integer noOfEmp = resultSet.getInt("noOfEmp");
                String salonType = resultSet.getString("salonType");
                String salonDescription = resultSet.getString("salonDescription");

                Salon salon = new Salon(id, salonName, salonOwner, salonAddress, salonContact, noOfEmp, salonType, salonDescription);
                salons.add(salon);
            }

        }catch(SQLException e){
            System.out.println("Fetching failed!!!");
        }
        return salons;
    }
}
