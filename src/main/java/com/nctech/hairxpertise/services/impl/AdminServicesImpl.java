package com.nctech.hairxpertise.services.impl;

import com.nctech.hairxpertise.dtos.Admin;
import com.nctech.hairxpertise.repository.AdminRepository;
import com.nctech.hairxpertise.repository.impl.AdminRepositoryImpl;
import com.nctech.hairxpertise.services.AdminServices;

import java.util.List;

public class AdminServicesImpl implements AdminServices {

    AdminRepository adminRepository = new AdminRepositoryImpl();

    /**
     * @param admin
     */
    @Override
    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    /**
     * @return
     */
    @Override
    public List<Admin> fetchAll() {
        return adminRepository.get();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Admin fetchByID(Integer id) {
        for (Admin admin : adminRepository.get()){
            if (admin.getId().equals(id))
                return admin;
        }
        return null;
    }

    /**
     * @param admin
     */
    @Override
    public void update(Admin admin) {
        Admin tempUser = fetchByID(admin.getId());
        tempUser.setUserName(admin.getUserName());
        tempUser.setPassword(admin.getPassword());
        tempUser.setFullName(admin.getFullName());
        tempUser.setGender(admin.getGender());
        tempUser.setAge(admin.getAge());
        tempUser.setContact(admin.getContact());
        tempUser.setEmail(admin.getEmail());
        tempUser.setAddress(admin.getAddress());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        Admin admin = fetchByID(id);
        return adminRepository.get().remove(admin);
    }
}
