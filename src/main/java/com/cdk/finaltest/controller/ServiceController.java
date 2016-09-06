package com.cdk.finaltest.controller;

import com.cdk.finaltest.service.CarService;
import com.cdk.finaltest.dto.CarServiceDTO;
import com.cdk.finaltest.util.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

/**
 * Created by bagades on 9/3/2016.
 */
@Controller
public class ServiceController {
    @Autowired
    private CarService carService;
    public CarService getCarServiceDAO() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/addVehicleForService",method = RequestMethod.GET)
    public @ResponseBody
    String addforRepair(HttpServletRequest request, HttpServletResponse response){
        String reply;
        if(request.getSession().getAttribute("id")!= null) {
            System.out.println("hello");
            int vin = Integer.parseInt(request.getParameter("vin"));
            System.out.println(request.getParameter("dateOfServicing"));
            String type = request.getParameter("type");
            java.util.Date utilDate = DateUtility.stringToDate(request.getParameter("dateOfServicing"), "yyyy-MM-dd");
            Date sqlDate = new Date(utilDate.getTime());
            System.out.println("hello");
            CarServiceDTO carServiceDTO = new CarServiceDTO();
            carServiceDTO.setServiceType(type);
            carServiceDTO.setVin(vin);
            carServiceDTO.setDateOfServicing(sqlDate);
            carServiceDTO.setStatus("not start");
            carServiceDTO = carService.addForRepair(carServiceDTO);
            System.out.println(carServiceDTO);
            if (carServiceDTO == null) {
                reply = "booking full";
            } else
                reply = "successfully";
        }
        else
            reply = "invalid";
        return reply;
    }
    @RequestMapping(value = "/checkAvailable",method = RequestMethod.GET)
    public @ResponseBody
    String checkAvailablity(HttpServletRequest request, HttpServletResponse response){
        String reply;
        if(request.getSession().getAttribute("id")!=null) {
            java.util.Date utilDate = DateUtility.stringToDate(request.getParameter("date"), "yyyy-MM-dd");
            Date sqlDate = new Date(utilDate.getTime());
            boolean flg = carService.checkAvailability(sqlDate);
            if (!flg) {
                reply = "booking full";
            } else
                reply = "You can book for the selected date";
        }
        else
            reply = "invalid";
        return reply;
    }

}
