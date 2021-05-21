package org.varsh.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.varsh.common.util.ApiUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CoreImpl {

    @Autowired
    private ApiUtil apiUtil;

    public String getJson(){
        List<Map<String,String>> list = new ArrayList<>();
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("Name","Varsh");
        jsonMap.put("Age","23");
        jsonMap.put("Occupation","{\"Engineer\":\"check\"}");
        Map<String, String> jsonMap2 = new HashMap<>();
        jsonMap2.put("Name","Luna");
        jsonMap2.put("Age","2");
        jsonMap2.put("Occupation","Guard");
        list.add(jsonMap);
        list.add(jsonMap2);
        return apiUtil.listToJson(list);
    }
}
