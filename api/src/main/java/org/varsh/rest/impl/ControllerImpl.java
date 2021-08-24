package org.varsh.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.varsh.common.util.ApiUtil;
import org.varsh.common.util.Constants;
import org.varsh.core.impl.CoreImpl;

import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
public class ControllerImpl {

    @Autowired
    private CoreImpl coreImpl;

    @Autowired
    private ApiUtil apiUtil;


    @GetMapping(value = "/getJson")
    @ResponseBody
    public ResponseEntity getJson() throws Exception {
        String response = "";
        ResponseEntity responseData = null;
        try {
            response = coreImpl.getJson();
        }catch(Exception e){
            responseData = apiUtil.constructError(Constants.getJSONException,e);
        }
        responseData = responseData!=null?responseData:ResponseEntity.ok(response);
        return responseData;
    }

    @GetMapping(value= "/v1/getJson")
    @ResponseBody
    public ResponseEntity getJsonList() throws Exception {
        List<Map<String,Object>> response = null;
        ResponseEntity responseData = null;
        try {
            response = coreImpl.getJsonList();
        }catch(Exception e){
            responseData = apiUtil.constructError(Constants.getJSONException,e);
        }
        responseData = responseData!=null?responseData:ResponseEntity.ok(response);
        return responseData;
    }

    @PostMapping(value= "/v1/updateContactDetails")
    @ResponseBody
    public ResponseEntity updateContactDetails(@RequestBody String data) throws Exception {
        String response = null;
        ResponseEntity responseData = null;
        try {
            response = coreImpl.updateContactDetails(data);
        }catch(Exception e){
            responseData = apiUtil.constructError(Constants.updateContactDetails,e);
        }
        responseData = responseData!=null?responseData:ResponseEntity.ok(response);
        return responseData;
    }

    @GetMapping(value= "/v1/getSlots")
    @ResponseBody
    public ResponseEntity getSlots() throws Exception {
        Map<String,String> response = null;
        ResponseEntity responseData = null;
        try {
            response = coreImpl.getSlots();
        }catch(Exception e){
            responseData = apiUtil.constructError(Constants.getSlots,e);
        }
        responseData = responseData!=null?responseData:ResponseEntity.ok(response);
        return responseData;
    }

    @PostMapping(value= "/v1/updateSlotsAndUser")
    @ResponseBody
    public ResponseEntity updateSlotsAndUser(@RequestBody String data) throws Exception {
        String response = null;
        ResponseEntity responseData = null;
        try {
            response = coreImpl.updateSlotsAndUser(data);
        }catch(Exception e){
            responseData = apiUtil.constructError(Constants.updateSlotsAndUser,e);
        }
        responseData = responseData!=null?responseData:ResponseEntity.ok(response);
        return responseData;
    }

    @PostMapping(value= "/v1/deleteUserSlot")
    @ResponseBody
    public ResponseEntity deleteUserSlot(@RequestBody String data) throws Exception {
        String response = null;
        ResponseEntity responseData = null;
        try {
            response = coreImpl.deleteUserSlot(data);
        }catch(Exception e){
            responseData = apiUtil.constructError(Constants.deleteUserSlot,e);
        }
        responseData = responseData!=null?responseData:ResponseEntity.ok(response);
        return responseData;
    }

    @GetMapping(value= "/v1/getAdoptPupDetails")
    @ResponseBody
    public ResponseEntity getAdoptPupDetails() throws Exception {
        List<Map<String,Object>> response = null;
        ResponseEntity responseData = null;
        try {
            response = coreImpl.getAdoptPupDetails();
        }catch(Exception e){
            responseData = apiUtil.constructError(Constants.getAdoptPupDetails,e);
        }
        responseData = responseData!=null?responseData:ResponseEntity.ok(response);
        return responseData;
    }

}
