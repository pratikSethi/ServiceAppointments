package com.cdk.finaltest.dto;

import java.sql.Date;

/**
 * Created by bagades on 8/30/2016.
 */
public class Vehicle {
    private int vin;
    private int customerId;
    private String model;
    private String company;
    private Date dateOfPurchase;

    @Override
    public String toString() {
        return "{ 'Vehicle':{" +
                "'vin':" + vin +
                ", 'customerId':" + customerId +
                ", 'model':'" + model + '\'' +
                ", 'company':'" + company + '\'' +
                ", 'dateOfPurchase':'" + dateOfPurchase+"'"+
                "}}";
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
}
