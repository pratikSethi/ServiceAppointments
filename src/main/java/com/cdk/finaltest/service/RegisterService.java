package com.cdk.finaltest.service;

import com.cdk.finaltest.dao.CustomerDAO;
import com.cdk.finaltest.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by agrawaay on 9/2/2016.
 */
@Service
public class RegisterService {
    @Autowired
    private CustomerDAO customerDAO = null;
    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }
    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Customer save(Customer customer) {
        Customer customer1 = customerDAO.save(customer);
        return customer1;
    }

}
