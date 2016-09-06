package com.cdk.finaltest.service;

import com.cdk.finaltest.dao.CarServiceDAO;
import com.cdk.finaltest.dto.CarServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

/**
 * Created by bagades on 9/3/2016.
 */
@Component
public class CarService {
    @Autowired
    CarServiceDAO carServiceDAO;

    public CarServiceDAO getCarServiceDAO() {
        return carServiceDAO;
    }

    public void setCarServiceDAO(CarServiceDAO carServiceDAO) {
        this.carServiceDAO = carServiceDAO;
    }
    public boolean checkAvailability(Date date){
        int count = carServiceDAO.checkAvailablity(date);
        System.out.println(count);
        return count < 5;
    }
    public CarServiceDTO addForRepair(CarServiceDTO carServiceDTO){

        CarServiceDTO carServiceDTO1 =  carServiceDAO.addForRepair(carServiceDTO);
        return carServiceDTO1;
    }
    public String getLastServiceDetails(int vin){

        return carServiceDAO.getLastServiceDetails(vin);
    }
}
