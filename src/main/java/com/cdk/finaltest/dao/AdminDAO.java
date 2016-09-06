package com.cdk.finaltest.dao;

import com.cdk.finaltest.domain.CarService;
import com.cdk.finaltest.dto.CarServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

/**
 * Created by bagades on 9/3/2016.
 */
@Component
public class AdminDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    public List<Object[]> getVehiclesByDate(Date sqlDate){
       return hibernateTemplate.findByNamedParam("select c.serviceId,c.dateOfServicing,c.serviceType,c.status,p.name,p.address from com.cdk.finaltest.domain.CarService c, com.cdk.finaltest.domain.Customer p, com.cdk.finaltest.domain.Vehicle v where v.vin=c.vin and v.customerId=p.customerId and c.dateOfServicing=:sqlDate","sqlDate",sqlDate);
    }
    public CarServiceDTO updateStatus(int serviceId, String status){
        CarService carService = hibernateTemplate.get(CarService.class,serviceId);
        carService.setStatus(status);
        hibernateTemplate.update(carService);
        CarServiceDTO carServiceDTO = new CarServiceDTO();
        carServiceDTO.setServiceId(carService.getServiceId());
        carServiceDTO.setServiceType(carService.getServiceType());
        carServiceDTO.setDateOfServicing(carService.getDateOfServicing());
        carServiceDTO.setStatus(carService.getStatus());
        carServiceDTO.setVin(carService.getVin());
        return carServiceDTO;
    }
}
