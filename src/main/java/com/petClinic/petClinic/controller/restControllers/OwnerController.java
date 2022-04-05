package com.petClinic.petClinic.controller.restControllers;

import com.petClinic.petClinic.entity.Owner;
import com.petClinic.petClinic.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping(path="rest")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping(path = "addOwner")
    Map<String,Object> goToLogin(Model model,
         HttpSession session,
         @RequestParam("fName") String fName,
         @RequestParam("lName") String lName,
         @RequestParam("dateOfBirth") String dateOfBirth,
         @RequestParam("registrationDate") String registrationDate,
         @RequestParam("city") String city,
         @RequestParam("street") String street,
         @RequestParam("house") int house,
         @RequestParam("apartment") int apartment,
         @RequestParam("projectSerialNumber") int projectSerialNumber){
        Map<String,Object> jsonObj = new HashMap<>();
        int status = 1;
        String message = "";

        if(fName.equals("")){

            return loadMessage(jsonObj, -1, "fName is empty");
        }

        if(lName.equals("")){

            return loadMessage(jsonObj, -1, "lName is empty");
        }

        if(dateOfBirth.equals("")){

            return loadMessage(jsonObj, -1, "dateOfBirth is empty");
        }

        if(registrationDate.equals("")){

            return loadMessage(jsonObj, -1, "registrationDate is empty");
        }

        if(city.equals("")){

            return loadMessage(jsonObj, -1, "city is empty");
        }

        if(street.equals("")){

            return loadMessage(jsonObj, -1, "street is empty");
        }

        if(house < 1){

            return loadMessage(jsonObj, -1, "house must be higher then 0");
        }

        if(apartment < 1){

            return loadMessage(jsonObj, -1, "house must be apartment then 0");
        }

        Owner owner = new Owner(fName,lName,dateOfBirth,registrationDate,
                city,street,house,apartment,projectSerialNumber);

        ownerService.addOwner(owner);

        return loadMessage(jsonObj, 1, "owner added successfully");
    }

    private Map<String,Object> loadMessage(Map<String,Object> jsonObj, int status, String message){

        jsonObj.put("status", status);
        jsonObj.put("message", message);
        return jsonObj;
    }
}