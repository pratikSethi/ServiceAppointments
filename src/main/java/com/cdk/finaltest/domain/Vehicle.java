package com.cdk.finaltest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by bagades on 8/30/2016.
 */
@Table(name = "vehicle_t")
@Entity
public class Vehicle {
    @Id
    @Column(name = "vin")
    private int vin;
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "model")
    private String model;
    @Column(name = "company")
    private String company;
    @Column(name="date_of_purchase")
    private Date dateOfPurchase;

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
