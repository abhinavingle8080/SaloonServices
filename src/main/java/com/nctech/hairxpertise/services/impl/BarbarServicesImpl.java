package com.nctech.hairxpertise.services.impl;

import com.nctech.hairxpertise.dtos.Barbar;
import com.nctech.hairxpertise.repository.BarbarRepository;
import com.nctech.hairxpertise.repository.BarbarRepository;
import com.nctech.hairxpertise.repository.impl.BarbarRepositoryImpl;
import com.nctech.hairxpertise.services.BarbarServices;

import java.util.List;

public class BarbarServicesImpl implements BarbarServices {

    BarbarRepository barbarRepository = new BarbarRepositoryImpl();

    /**
     * @param barbar
     */
    @Override
    public void save(Barbar barbar) {
        barbarRepository.save(barbar);
    }

    /**
     * @return
     */
    @Override
    public List<Barbar> fetchAll() {
        return barbarRepository.get();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Barbar fetchByID(Integer id) {
        for (Barbar barbar : barbarRepository.get()){
            if (barbar.getId().equals(id))
                return barbar;
        }
        return null;
    }

    /**
     * @param barbar
     */
    @Override
    public void update(Barbar barbar) {
        Barbar tempBarbar = fetchByID(barbar.getId());
        tempBarbar.setUserName(barbar.getUserName());
        tempBarbar.setPassword(barbar.getPassword());
        tempBarbar.setFullName(barbar.getFullName());
        tempBarbar.setGender(barbar.getGender());
        tempBarbar.setAge(barbar.getAge());
        tempBarbar.setContact(barbar.getContact());
        tempBarbar.setEmail(barbar.getEmail());
        tempBarbar.setAddress(barbar.getAddress());
        tempBarbar.setExpertise(barbar.getExpertise());
        tempBarbar.setWorkPlace(barbar.getWorkPlace());
        
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        Barbar barbar = fetchByID(id);
        return barbarRepository.get().remove(barbar);
    }
}
