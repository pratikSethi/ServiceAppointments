package com.cdk.finaltest.controller;


import com.cdk.finaltest.dao.VehicleDAO;
import com.cdk.finaltest.dto.CarServiceDTO;
import com.cdk.finaltest.service.CarService;
import com.cdk.finaltest.dto.Vehicle;
import com.cdk.finaltest.service.VehicleService;
import com.cdk.finaltest.util.DateUtility;
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
import java.sql.Date;
import java.util.List;

/**
 * Created by bagades on 8/30/2016.
 */
@Controller
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private CarService carService;

    public CarService getCarService() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @RequestMapping(value = "/addVehicle",method = RequestMethod.POST)
    public @ResponseBody
    Vehicle add(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(request.getSession().getAttribute("id")!=null) {
            int id = (int) session.getAttribute("id");
            Vehicle vehicle = new Vehicle();
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            ServletFileUpload sf = new ServletFileUpload(diskFileItemFactory);
            try {
                List<FileItem> fileItemList = sf.parseRequest(request);
                for (FileItem fileItem : fileItemList) {
                    System.out.println("1");
                    if (fileItem.getFieldName().equals("vin")) {
                        vehicle.setVin(Integer.parseInt(fileItem.getString()));
                    } else if (fileItem.getFieldName().equals("model")) {
                        vehicle.setModel(fileItem.getString());
                    } else if (fileItem.getFieldName().equals("company")) {
                        vehicle.setCompany(fileItem.getString());
                    } else if (fileItem.getFieldName().equals("date_of_purchase")) {
                        System.out.println(fileItem.getString());
                        vehicle.setDateOfPurchase(new Date(DateUtility.stringToDate(fileItem.getString(), "yyyy-MM-dd").getTime()));
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            vehicle.setCustomerId(id);
            return vehicleService.save(vehicle);
        }
        return null;
    }
    @RequestMapping(value = "/listVehicle",method = RequestMethod.POST)
    public @ResponseBody
    List<Vehicle> list(HttpServletRequest request, HttpServletResponse response){
        if(request.getSession().getAttribute("id")!=null) {
            return vehicleService.list();
        }
        return null;
    }
    @RequestMapping(value = "/getVehicleDetails",method = RequestMethod.POST)
    public @ResponseBody
    Vehicle getVehicle(HttpServletRequest request, HttpServletResponse response, int vin){
        if(request.getSession().getAttribute("id")!=null) {
            return vehicleService.get(vin);
        }
        return null;
    }
    @RequestMapping(value = "/customerVehicleList",method = RequestMethod.POST)
    public @ResponseBody
    String customerVehicleList(HttpServletRequest request, HttpServletResponse response){
        if(request.getSession().getAttribute("id")!=null) {
            System.out.println(request.getSession().getAttribute("id"));
            List<Vehicle> vehicleList = vehicleService.customerVehicleList((int) request.getSession().getAttribute("id"));
            CarServiceDTO carServiceDTO = null;
            String status = "";
            String str = "{\"Vehicles\":[";
            for (Vehicle vehicle : vehicleList) {
                status = carService.getLastServiceDetails(vehicle.getVin());
                str += "{" +
                        "\"vin\":\"" + vehicle.getVin() + "\"" +
                        ", \"customerId\":\"" + vehicle.getCustomerId() + "\"" +
                        ", \"model\":\"" + vehicle.getModel() + '\"' +
                        ", \"status\":\"" + status + '\"' +
                        ", \"company\":\"" + vehicle.getCompany() + '\"' +
                        ", \"dateOfPurchase\":\"" + vehicle.getDateOfPurchase() + "\"" +
                        "},";
            }
            str = str.substring(0, str.length() - 1);
            str += "]}";
            System.out.println(str);
            return str;
        }
        return null;
    }
}
