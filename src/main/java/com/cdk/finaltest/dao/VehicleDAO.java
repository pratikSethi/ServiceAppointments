package com.cdk.finaltest.dao;


import com.cdk.finaltest.dto.Vehicle;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagades on 8/30/2016.
 */
public class VehicleDAO {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    public Vehicle save(Vehicle vehicle){
        com.cdk.finaltest.domain.Vehicle domainVehicle =
                new com.cdk.finaltest.domain.Vehicle();
        if(domainVehicle!=null) {
            domainVehicle.setVin(vehicle.getVin());
            domainVehicle.setCustomerId(vehicle.getCustomerId());
            domainVehicle.setCompany(vehicle.getCompany());
            domainVehicle.setDateOfPurchase(vehicle.getDateOfPurchase());
            domainVehicle.setModel(vehicle.getModel());
            hibernateTemplate.save(domainVehicle);
            return vehicle;
        }
         return null;

    }
    public Vehicle get(int vin){
        com.cdk.finaltest.domain.Vehicle domainVehicle = (com.cdk.finaltest.domain.Vehicle) hibernateTemplate.get(com.cdk.finaltest.domain.Vehicle.class,vin);
        if(domainVehicle!=null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVin(domainVehicle.getVin());
            vehicle.setModel(domainVehicle.getModel());
            vehicle.setCompany(domainVehicle.getCompany());
            vehicle.setDateOfPurchase(domainVehicle.getDateOfPurchase());
            vehicle.setCustomerId(domainVehicle.getCustomerId());
            return vehicle;
        }
        return null;
    }
    public List<Vehicle> list(){
        List<com.cdk.finaltest.domain.Vehicle> domainVehicleList = hibernateTemplate.loadAll(com.cdk.finaltest.domain.Vehicle.class);
        List<Vehicle> dtoVehicleList = null;
        if (null != domainVehicleList && domainVehicleList.size() != 0) {
            dtoVehicleList = new ArrayList<>();
            for (com.cdk.finaltest.domain.Vehicle v : domainVehicleList) {
                Vehicle vehicle= new Vehicle();
                vehicle.setVin(v.getVin());
                vehicle.setModel(v.getModel());
                vehicle.setCompany(v.getCompany());
                vehicle.setDateOfPurchase(v.getDateOfPurchase());
                vehicle.setCustomerId(v.getCustomerId());
                dtoVehicleList.add(vehicle);
            }
        }
        return dtoVehicleList;
    }
    public List<Vehicle> customerVehicleList(int customer_id) {
        List<com.cdk.finaltest.domain.Vehicle> domainVehicleList = hibernateTemplate.findByNamedParam("from com.cdk.finaltest.domain.Vehicle v where v.customerId =:customer_id", "customer_id", customer_id);
        System.out.println("hello");
        List<Vehicle> dtoVehicleList = null;
        if (null != domainVehicleList && domainVehicleList.size() != 0) {
            dtoVehicleList = new ArrayList<>();
            for (com.cdk.finaltest.domain.Vehicle v : domainVehicleList) {
                Vehicle vehicle= new Vehicle();
                vehicle.setVin(v.getVin());
                vehicle.setModel(v.getModel());
                vehicle.setCompany(v.getCompany());
                vehicle.setDateOfPurchase(v.getDateOfPurchase());
                vehicle.setCustomerId(v.getCustomerId());
                dtoVehicleList.add(vehicle);
                System.out.println(vehicle);
            }
        }
        System.out.println(dtoVehicleList.size());
        return dtoVehicleList;
    }
}
