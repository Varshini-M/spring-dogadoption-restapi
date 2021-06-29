package org.varsh.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.varsh.common.util.ApiUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CoreImpl {

    @Autowired
    private ApiUtil apiUtil;

    public String getJson() throws Exception {
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

    public List<Map<String,Object>> getJsonList() throws Exception {
        byte[] jsonFile = Files.readAllBytes(Paths.get("C:\\Varshini\\workspace\\UI\\test-api\\json"));
        List<Map<String,Object>> list = ApiUtil.generateMapFromJsonList(new String(jsonFile));
        return list;
    }

    public String updateContactDetails(String metaData) throws Exception {
        byte[] jsonFile = Files.readAllBytes(Paths.get("C:\\Varshini\\workspace\\UI\\test-api\\contactJson"));
        String contactJson = new String(jsonFile);
        List contactList = apiUtil.generateMapFromJsonList(contactJson);
        contactList.add(apiUtil.generateMapFromJsonString(metaData));
        Files.write(Paths.get("C:\\Varshini\\workspace\\UI\\test-api\\contactJson"),apiUtil.listToJson(contactList).getBytes());
        Map<String,String> response = new HashMap<>();
        response.put("success","true");
        return apiUtil.mapToJson(response);
    }

    public Map<String,String> getSlots()throws Exception{
        byte[] jsonFile = Files.readAllBytes(Paths.get("C:\\Varshini\\workspace\\UI\\test-api\\slotsJson"));
        String slotsJson = new String(jsonFile);
        return apiUtil.generateMapFromJsonString(slotsJson);
    }

    public String updateSlotsAndUser(String data)throws Exception{
        byte[] jsonFile = Files.readAllBytes(Paths.get("C:\\Varshini\\workspace\\UI\\test-api\\slotsJson"));
        String slotsJson = new String(jsonFile);
        Map<String,String> slotMap = apiUtil.generateMapFromJsonString(slotsJson);
        Map<String,String> requestMap = apiUtil.generateMapFromJsonString(data);
        Map<String,String> userSlotJson = new HashMap<>();
        userSlotJson.put("email",requestMap.get("email"));
        userSlotJson.put("slot",requestMap.get("slot"));
        int slots = Integer.parseInt(slotMap.get(requestMap.get("slot")));
        slotMap.put(requestMap.get("slot"),slots+1+"");
        Files.write(Paths.get("C:\\Varshini\\workspace\\UI\\test-api\\slotsJson"),apiUtil.mapToJson(slotMap).getBytes());
        Files.write(Paths.get("C:\\Varshini\\workspace\\UI\\test-api\\userSlotJson"),apiUtil.mapToJson(userSlotJson).getBytes());
        return data;
    }
}
