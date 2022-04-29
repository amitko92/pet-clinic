package com.petClinic.petClinic.controller.restControllers;

import com.petClinic.petClinic.entity.Owner;
import com.petClinic.petClinic.service.OwnerService;
import com.petClinic.petClinic.core.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController()
@RequestMapping(path="rest")
public class OwnerController {

    private final OwnerService ownerService;
    private final SessionManager sessionManager;

    @Autowired
    public OwnerController(OwnerService ownerService, SessionManager sessionManager) {
        this.ownerService = ownerService;
        this.sessionManager = sessionManager;
    }

    @PostMapping(path = "owner")
    public Map<String,Object> getOwnerById(
            HttpSession session,
            @RequestParam("ownerId") int ownerId){

        Map<String,Object> jsonObj = new HashMap<>();
        // check is has session.


        // try to retrieve user from DB.
        Optional<Owner> opOwner = ownerService.getOwnerById(ownerId);

        // putting user object in json if founds, if not return error.
        if(opOwner.isPresent()){

            jsonObj.put("owner", opOwner.get());

            loadMessage(jsonObj, 1, "success");
        }else{

            loadMessage(jsonObj, -1, "failed to find owner with id: " + ownerId);
        }

        //  return json.
        return jsonObj;
    }

    @PostMapping(path = "addOwner")
    Map<String,Object> addOwner(
         HttpSession session,
         @RequestParam("fName") String fName,
         @RequestParam("lName") String lName,
         @RequestParam("dateOfBirth") String dateOfBirth,
         @RequestParam("registrationDate") String registrationDate,
         @RequestParam("city") String city,
         @RequestParam("street") String street,
         @RequestParam("house") int house,
         @RequestParam("apartment") int apartment){


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
                city,street,house,apartment,1);

        System.out.println("owner " + owner.toString());
        ownerService.addOwner(owner);

        return loadMessage(jsonObj, 1, "owner added successfully");
    }

    private Map<String,Object> loadMessage(Map<String,Object> jsonObj, int status, String message){

        jsonObj.put("status", status);
        jsonObj.put("message", message);
        return jsonObj;
    }
}
