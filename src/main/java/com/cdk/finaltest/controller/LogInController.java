package com.cdk.finaltest.controller;


import com.cdk.finaltest.dto.Customer;
import com.cdk.finaltest.service.LoginService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by bagades on 8/30/2016.
 */
@Controller
@RequestMapping(value = "/")
public class LogInController {

    @Autowired
    private LoginService loginService = null;

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public
    @ResponseBody
    Customer get(HttpServletRequest request, HttpServletResponse response) {
        String email = "";
        String password = "";
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload sf = new ServletFileUpload(diskFileItemFactory);
        try {
            List<FileItem> fileItemList = sf.parseRequest(request);
            for (FileItem fileItem : fileItemList) {
                if (fileItem.getFieldName().equals("email")) {
                    email = fileItem.getString();
                } else if (fileItem.getFieldName().equals("password")) {
                    password = fileItem.getString();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        if(email.equals("admin@admin.com")&&password.equals("admin")){
            HttpSession session = request.getSession();
            session.setAttribute("id", "admin");
            Customer customer =  new Customer();
            customer.setEmail("admin@admin.com");
            return customer;
        }
        Customer customer = loginService.get(email, password);

        HttpSession session = request.getSession();
        session.setAttribute("id", customer.getCustomerId());

        return customer;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public
    @ResponseBody
    String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "logout successfully";
    }
}