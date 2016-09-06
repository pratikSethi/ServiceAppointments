package com.cdk.finaltest.dao;

import com.cdk.finaltest.dto.Customer;
import org.hibernate.JDBCException;
import org.hibernate.util.JDBCExceptionReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagades on 8/29/2016.
 */
@Component
public class CustomerDAO {

    @Autowired
        private HibernateTemplate hibernateTemplate;

        public HibernateTemplate getHibernateTemplate() {
            return hibernateTemplate;
        }

        public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
            this.hibernateTemplate = hibernateTemplate;
        }
    public Customer getCustomer(String email, String password){
        List<com.cdk.finaltest.domain.Customer> domainList =  hibernateTemplate.findByNamedParam("from com.cdk.finaltest.domain.Customer p where p.email=:email and p.password=:password",new String[]{"email","password"},new Object[]{email,password});

        Customer customer = null;
        if  (domainList.size() == 1)
        {
            customer = new Customer();
            com.cdk.finaltest.domain.Customer c = domainList.get(0);

            customer.setCustomerId(c.getCustomerId());
            customer.setName(c.getName());
            customer.setEmail(c.getEmail());
            customer.setPassword(c.getPassword());
            customer.setPhoneNo(c.getPhoneNo());
            customer.setAddress(c.getAddress());
            customer.setCity(c.getCity());
            customer.setState(c.getState());
            customer.setPostalCode(c.getPostalCode());

        }
        System.out.println(customer);
        return customer;
    }
        public Customer save(Customer customer) {

            com.cdk.finaltest.domain.Customer domainCustomer = new com.cdk.finaltest.domain.Customer();
            domainCustomer.setName(customer.getName());
            domainCustomer.setPhoneNo(customer.getPhoneNo());
            domainCustomer.setEmail(customer.getEmail());
            domainCustomer.setAddress(customer.getAddress());
            domainCustomer.setPassword(customer.getPassword());
            domainCustomer.setCity(customer.getCity());
            domainCustomer.setState(customer.getState());
            domainCustomer.setPostalCode(customer.getPostalCode());
            hibernateTemplate.save(domainCustomer);
            customer.setCustomerId(domainCustomer.getCustomerId());
            return customer;
        }
        public Customer get(int  customer_id){
            com.cdk.finaltest.domain.Customer domainCustomer= hibernateTemplate.get(com.cdk.finaltest.domain.Customer.class,customer_id);
            Customer customer = new Customer();
            customer.setCustomerId(domainCustomer.getCustomerId());
            customer.setName(domainCustomer.getName());
            customer.setEmail(domainCustomer.getEmail());
            customer.setPhoneNo(domainCustomer.getPhoneNo());
            customer.setPassword(domainCustomer.getPassword());
            customer.setAddress(domainCustomer.getAddress());
            customer.setCity(domainCustomer.getCity());
            customer.setState(domainCustomer.getState());
            customer.setPostalCode(domainCustomer.getPostalCode());
            return customer;
        }
        public List<Customer> list(){
            List<com.cdk.finaltest.domain.Customer> domainCustomerList = hibernateTemplate.loadAll(com.cdk.finaltest.domain.Customer.class);
            List<Customer> dtoCustomerList = null;
            if (null != domainCustomerList && domainCustomerList.size() != 0) {
                dtoCustomerList = new ArrayList<>();
                for (com.cdk.finaltest.domain.Customer c : domainCustomerList) {
                    Customer customer = new Customer();
                    customer.setCustomerId(c.getCustomerId());
                    customer.setName(c.getName());
                    customer.setEmail(c.getEmail());
                    customer.setPassword(c.getPassword());
                    customer.setPhoneNo(c.getPhoneNo());
                    customer.setAddress(c.getAddress());
                    customer.setCity(c.getCity());
                    customer.setState(c.getState());
                    customer.setPostalCode(c.getPostalCode());
                    dtoCustomerList.add(customer);
                }

            }
            return dtoCustomerList;
        }
}


