package com.cdk.finaltest.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by bagades on 9/3/2016.
 */
@Table(name="carservice_t")
@Entity
public class CarService {
    @Id
    @GeneratedValue
    @Column(name="service_id")
    private int serviceId;
    @Column(name="vin")
    private int vin;
    @Column(name = "date_of_servicing")
    private Date dateOfServicing;
    @Column(name = "service_type")
    private String serviceType;
    @Column(name = "status")
    private String status;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public Date getDateOfServicing() {
        return dateOfServicing;
    }

    public void setDateOfServicing(Date dateOfServicing) {
        this.dateOfServicing = dateOfServicing;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
