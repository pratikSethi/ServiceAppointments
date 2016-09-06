package com.cdk.finaltest.service;

import com.cdk.finaltest.dao.CarServiceDAO;
import com.cdk.finaltest.dao.VehicleDAO;
import com.cdk.finaltest.dto.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by bagades on 9/3/2016.
 */
@Controller
public class VehicleService {
    @Autowired
    private VehicleDAO vehicleDAO;

    public VehicleDAO getVehicleDAO() {
        return vehicleDAO;
    }

    public void setVehicleDAO(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }
    public Vehicle save(Vehicle vehicle){
        return vehicleDAO.save(vehicle);
    }
    public List<Vehicle> list(){
        return vehicleDAO.list();
    }
    public Vehicle get(int vin){
        return vehicleDAO.get(vin);
    }
    public List<Vehicle> customerVehicleList(int id){
        return  vehicleDAO.customerVehicleList(id);
    }

}
