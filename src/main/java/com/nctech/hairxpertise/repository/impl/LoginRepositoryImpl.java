package com.nctech.hairxpertise.repository.impl;

import com.nctech.hairxpertise.dtos.Admin;
import com.nctech.hairxpertise.dtos.Barbar;
import com.nctech.hairxpertise.dtos.Login;
import com.nctech.hairxpertise.dtos.User;
import com.nctech.hairxpertise.repository.LoginRepository;
import com.nctech.hairxpertise.services.AdminServices;
import com.nctech.hairxpertise.services.BarbarServices;
import com.nctech.hairxpertise.services.UserServices;
import com.nctech.hairxpertise.services.impl.AdminServicesImpl;
import com.nctech.hairxpertise.services.impl.BarbarServicesImpl;
import com.nctech.hairxpertise.services.impl.UserServicesImpl;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoginRepositoryImpl implements LoginRepository{

    private Connection getConnection() throws SQLException {
        String url = "jdbc:h2:file:./stddb";
        String username = "sa";
        String password = "sa";
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * @param login
     * @return
     */
    @Override
    public String createLogin(Login login) {

        if(isAdmin(login)){
            save(login);
            return "Logged in as Admin";
        }


        if(isUser(login)){
            save(login);
            return "Logged in as User";
        }


        if (isBarbar(login)){
            save(login);
            return "Logged in as Barbar";
        }


        return null;
    }

    public void save(Login login) {
        login.setId(get().size());

        LocalDateTime localDateTime = LocalDateTime.now();

        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO Logins VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, login.getId());
            statement.setString(2, login.getUserName());
            statement.setString(3, localDateTime.toString());

            statement.executeUpdate();
            System.out.println("Saved successfully");
        } catch (SQLException e) {
            System.out.println("Save Failed");
        }
    }

    public List<Login> get() {
        List<Login> users = new ArrayList<>();

        try (Connection connection = getConnection()) {

            String sql = "SELECT id, LoggedAs, LoggedTime FROM Logins";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String loggedAs = resultSet.getString ("loggedAs");
                String loggedTime = resultSet.getString("loggedTime");

                Login login = new Login(id, loggedAs, loggedTime );
                users.add(login);
            }

        }catch(SQLException e){
            System.out.println("Fetching failed!!!");
        }
        return users;
    }

    public boolean isAdmin(Login login){
        AdminServices adminServices = new AdminServicesImpl();
        for(Admin admin : adminServices.fetchAll()){
            if(admin.getUserName().equals(login.getUserName()) && admin.getPassword().equals(login.getPassword())){
                return true;
            }
        }
        return false;
    }

    public boolean isUser(Login login){
        UserServices userServices = new UserServicesImpl();
        for(User user : userServices.fetchAll()){
            if(user.getUserName().equals(login.getUserName()) && user.getPassword().equals(login.getPassword())){
                return true;
            }
        }
        return false;
    }

    public boolean isBarbar(Login login){
        BarbarServices barbarServices = new BarbarServicesImpl();
        for(Barbar barbar : barbarServices.fetchAll()){
            if(barbar.getUserName().equals(login.getUserName()) && barbar.getPassword().equals(login.getPassword())){
                return true;
            }
        }
        return false;
    }
}
