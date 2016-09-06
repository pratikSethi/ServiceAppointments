package com.cdk.finaltest.controller;

import com.cdk.finaltest.dto.CarServiceDTO;
import com.cdk.finaltest.dto.Vehicle;
import com.cdk.finaltest.service.AdminService;
import com.cdk.finaltest.util.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

/**
 * Created by bagades on 9/3/2016.
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    public AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
    @RequestMapping(value = "/updateStatus",method = RequestMethod.GET)
    public @ResponseBody String updateStatus(HttpServletRequest request, HttpServletResponse response){
        int serviceId = Integer.parseInt(request.getParameter("sid"));
        String status = request.getParameter("status");
        CarServiceDTO carServiceDTO = adminService.updateStatus(serviceId,status);
        String result = "Successfully update status of "+carServiceDTO.getServiceId()+" to "+carServiceDTO.getStatus();
        return result;
    }
    @RequestMapping(value = "/getDetailsByDate", method = RequestMethod.GET)
    public @ResponseBody String getDetailsByDate(HttpServletRequest request, HttpServletResponse response){
        String date = request.getParameter("date");
        Date sqlDate = new Date(DateUtility.stringToDate(date,"yyyy-MM-dd").getTime());
        List<Object[]> vehicleList= adminService.getVehiclesByDate(sqlDate);
        String str = "{\"Vehicles\":[";
        for (Object[] vehicle : vehicleList) {
            str += "{" +
                    "\"serviceId\":\"" + vehicle[0] + "\"" +
                    ", \"dateOfService\":\"" + vehicle[1] + "\"" +
                    ", \"type\":\"" + vehicle[2] + '\"' +
                    ", \"status\":\"" + vehicle[3] + '\"' +
                    ", \"name\":\"" + vehicle[4] + '\"' +
                    ", \"address\":\"" + vehicle[5] + "\"" +
                    "},";
        }
        str = str.substring(0, str.length() - 1);
        str += "]}";
        System.out.println(str);

        return str;
    }
}
