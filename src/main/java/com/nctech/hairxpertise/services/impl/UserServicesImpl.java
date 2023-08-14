package com.nctech.hairxpertise.services.impl;

import com.nctech.hairxpertise.dtos.User;
import com.nctech.hairxpertise.repository.UserRepository;
import com.nctech.hairxpertise.repository.impl.UserRepositoryImpl;
import com.nctech.hairxpertise.services.UserServices;

import java.util.List;

public class UserServicesImpl implements UserServices {

    UserRepository userRepository = new UserRepositoryImpl();

    /**
     * @param user
     */
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * @return
     */
    @Override
    public List<User> fetchAll() {
        return userRepository.get();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User fetchByID(Integer id) {
        for (User user : userRepository.get()){
            if (user.getId().equals(id))
                return user;
        }
        return null;
    }

    /**
     * @param user
     */
    @Override
    public void update(User user) {
        User tempUser = fetchByID(user.getId());
        tempUser.setUserName(user.getUserName());
        tempUser.setPassword(user.getPassword());
        tempUser.setFullName(user.getFullName());
        tempUser.setGender(user.getGender());
        tempUser.setAge(user.getAge());
        tempUser.setContact(user.getContact());
        tempUser.setEmail(user.getEmail());
        tempUser.setAddress(user.getAddress());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        User user = fetchByID(id);
        return userRepository.get().remove(user);
    }
}
