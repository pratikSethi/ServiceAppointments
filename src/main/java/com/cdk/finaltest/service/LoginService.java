package com.cdk.finaltest.service;

import com.cdk.finaltest.dao.CustomerDAO;
import com.cdk.finaltest.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by agrawaay on 9/2/2016.
 */
@Service
public class LoginService {


    @Autowired
    private CustomerDAO customerDAO =null;

    public CustomerDAO getLogInDAO() {
        return customerDAO;
    }

    public void setLogInDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Customer get (String email,String password) {
        Customer customer = customerDAO.getCustomer(email,password);
        return customer;

    }
}
