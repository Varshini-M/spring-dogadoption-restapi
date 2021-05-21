package org.varsh.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.varsh.common.util.ApiUtil;
import org.varsh.common.util.Constants;
import org.varsh.core.impl.CoreImpl;

@CrossOrigin(origins = "*")
@RestController
public class ControllerImpl {

    @Autowired
    private CoreImpl coreImpl;

    @Autowired
    private ApiUtil apiUtil;


    @GetMapping(value = "/getJson")
    @ResponseBody
    public ResponseEntity getJson(){
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
}
