package com.cdk.finaltest.controller;

import com.cdk.finaltest.dao.CustomerDAO;
import com.cdk.finaltest.dto.Customer;
import com.cdk.finaltest.service.RegisterService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by bagades on 8/30/2016.
 */
@Controller
public class RegistrationController {
    @Autowired
    private RegisterService registerService = null;

    public RegisterService getRegisterService() {
        return registerService;
    }

    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public @ResponseBody
    Customer add(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("hi");
        Customer customer = new Customer();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload sf= new ServletFileUpload(diskFileItemFactory);
        try {
            List<FileItem> fileItemList = sf.parseRequest(request);
            for (FileItem fileItem:fileItemList) {
                System.out.println("1");
                if(fileItem.getFieldName().equals("email")) {
                    customer.setEmail(fileItem.getString());
                } else if(fileItem.getFieldName().equals("password")) {
                    customer.setPassword(fileItem.getString());
                }
                else if(fileItem.getFieldName().equals("name")) {
                    customer.setName(fileItem.getString());
                }
                else if(fileItem.getFieldName().equals("address")) {
                    customer.setAddress(fileItem.getString());
                }
                else if(fileItem.getFieldName().equals("phoneNo")) {
                    customer.setPhoneNo(Long.parseLong(fileItem.getString()));
                }
                else if(fileItem.getFieldName().equals("city")) {
                    customer.setCity(fileItem.getString());
                }
                else if(fileItem.getFieldName().equals("state")) {
                    customer.setState(fileItem.getString());
                }
                else if(fileItem.getFieldName().equals("postalCode")) {
                    customer.setPostalCode(Integer.parseInt(fileItem.getString()));
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
        System.out.println(customer);
        customer= registerService.save(customer) ;
        System.out.println(customer);
        return customer;
    }


}
