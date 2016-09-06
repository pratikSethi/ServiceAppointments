package com.cdk.finaltest.dao;

import com.cdk.finaltest.domain.CarService;
import com.cdk.finaltest.dto.CarServiceDTO;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagades on 9/3/2016.
 */
@Component
public class CarServiceDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    public int checkAvailablity(Date date){
        List<CarServiceDTO> carServiceDTOList = hibernateTemplate.findByNamedParam("from com.cdk.finaltest.domain.CarService where dateOfServicing=:date and status!='done'","date",date);
        System.out.println(carServiceDTOList.size());
        return carServiceDTOList.size();
    }
    public String getLastServiceDetails(int vin){
        List<CarService> carServiceDTOList = hibernateTemplate.findByCriteria(DetachedCriteria.forClass(com.cdk.finaltest.domain.CarService.class).addOrder(Order.asc("dateOfServicing")));
        String status = "";
        if(carServiceDTOList!=null){
            CarService carService = carServiceDTOList.get(0);
            status = carService.getStatus();
        }
        return status;
    }
    public CarServiceDTO addForRepair(CarServiceDTO carServiceDTO){
        CarService carService = new CarService();
        carService.setServiceType(carServiceDTO.getServiceType());
        carService.setStatus(carServiceDTO.getStatus());
        carService.setDateOfServicing(carServiceDTO.getDateOfServicing());
        carService.setVin(carServiceDTO.getVin());

        List<CarServiceDTO> carServiceDTOList = hibernateTemplate.findByNamedParam("from com.cdk.finaltest.domain.CarService where dateOfServicing=:dateOfServicing and status!='done'","dateOfServicing",carServiceDTO.getDateOfServicing());
        System.out.println("Size"+  carServiceDTOList.size());
        if (carServiceDTOList.size()<5) {
            hibernateTemplate.save(carService);
            carServiceDTO.setServiceId(carService.getServiceId());
        }
        else
            carServiceDTO = null;

        return carServiceDTO;
    }
    public List<CarServiceDTO> list(){
        List<com.cdk.finaltest.domain.CarService> domainCarServiceList = hibernateTemplate.loadAll(com.cdk.finaltest.domain.CarService.class);
        List<CarServiceDTO> dtoCarServiceList = null;
        if (null != domainCarServiceList && domainCarServiceList.size() != 0) {
            dtoCarServiceList = new ArrayList<>();
            for (com.cdk.finaltest.domain.CarService v :domainCarServiceList) {
                CarServiceDTO carServiceDTO= new CarServiceDTO();
                carServiceDTO.setVin(v.getVin());
                carServiceDTO.setServiceId(v.getServiceId());
                carServiceDTO.setServiceType(v.getServiceType());
                carServiceDTO.setStatus(v.getStatus());
                carServiceDTO.setDateOfServicing(v.getDateOfServicing());
                dtoCarServiceList.add(carServiceDTO);
            }
        }
        return dtoCarServiceList;
    }

}
