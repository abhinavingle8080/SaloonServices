package com.nctech.hairxpertise.services.impl;

import com.nctech.hairxpertise.dtos.Salon;
import com.nctech.hairxpertise.repository.SalonRepository;
import com.nctech.hairxpertise.repository.impl.SalonRepositoryImpl;
import com.nctech.hairxpertise.services.SalonServices;

import java.util.List;

public class SalonServicesImpl implements SalonServices {

    SalonRepository salonRepository = new SalonRepositoryImpl();
    
    /**
     * @param salon
     */
    @Override
    public void save(Salon salon) {
        salonRepository.save(salon);
    }

    /**
     * @return
     */
    @Override
    public List<Salon> fetchAll() {
        return salonRepository.get();
    }

    /**
     * @return
     */
    @Override
    public Salon fetchByID(Integer id) {
        for (Salon salon : salonRepository.get()){
            if(salon.getId().equals(id))
                return salon;
        }
        return null;
    }

    /**
     * @param salon
     */
    @Override
    public void update(Salon salon) {
        Salon tempSalon = fetchByID(salon.getId());
        tempSalon.setSalonName(salon.getSalonName());
        tempSalon.setSalonOwner(salon.getSalonOwner());
        tempSalon.setSalonAddress(salon.getSalonAddress());
        tempSalon.setSalonContact(salon.getSalonContact());
        tempSalon.setNoOfEmp(salon.getNoOfEmp());
        tempSalon.setSalonType(salon.getSalonType());
        tempSalon.setSalonDescription(salon.getSalonDescription());
    }

    /**
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        Salon salon = fetchByID(id);
        return salonRepository.get().remove(salon);
    }
}
