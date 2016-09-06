package com.cdk.finaltest.service;

import com.cdk.finaltest.dao.AdminDAO;
import com.cdk.finaltest.dao.CarServiceDAO;
import com.cdk.finaltest.dao.CustomerDAO;
import com.cdk.finaltest.dao.VehicleDAO;
import com.cdk.finaltest.domain.*;
import com.cdk.finaltest.dto.CarServiceDTO;
import com.cdk.finaltest.dto.Customer;
import com.cdk.finaltest.dto.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

/**
 * Created by bagades on 9/3/2016.
 */
@Component
public class AdminService {
    @Autowired
    private AdminDAO adminDAO;

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public List<Object[]> getVehiclesByDate(Date sqlDate){

        return adminDAO.getVehiclesByDate(sqlDate);
    }
    public CarServiceDTO updateStatus(int serviceId, String status){
        return adminDAO.updateStatus(serviceId,status);
    }
}
