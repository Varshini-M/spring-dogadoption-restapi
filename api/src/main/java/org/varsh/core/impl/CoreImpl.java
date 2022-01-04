package org.varsh.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.varsh.common.util.ApiUtil;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CoreImpl {

    @Autowired
    private ApiUtil apiUtil;

    /**
     * Methos to return dummy json data
     * @return String
     * @throws Exception
     */
    public String getJson() throws Exception {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("Name", "Varsh");
        jsonMap.put("Age", "23");
        jsonMap.put("Occupation", "{\"Engineer\":\"check\"}");
        Map<String, String> jsonMap2 = new HashMap<>();
        jsonMap2.put("Name", "Luna");
        jsonMap2.put("Age", "2");
        jsonMap2.put("Occupation", "Guard");
        list.add(jsonMap);
        list.add(jsonMap2);
        return apiUtil.listToJson(list);
    }

    /**
     * Method to get snippet json
     * @return List<Map<String, Object>>
     * @throws Exception
     */
    public List<Map<String, Object>> getJsonList() throws Exception {
        byte[] jsonFile = Files.readAllBytes(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\json"));
        List<Map<String, Object>> list = ApiUtil.generateListFromJsonString(new String(jsonFile));
        return list;
    }

    /**
     * Method to update contact details
     * @param metaData
     * @return String
     * @throws Exception
     */
    public String updateContactDetails(String metaData) throws Exception {
        byte[] jsonFile = Files.readAllBytes(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\contactJson"));
        String contactJson = new String(jsonFile);
        List contactList = apiUtil.generateListFromJsonString(contactJson);
        contactList.add(apiUtil.generateMapFromJsonString(metaData));
        Files.write(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\contactJson"), apiUtil.listToJson(contactList).getBytes());
        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        return apiUtil.mapToJson(response);
    }

    /**
     * Method to get slots json
     * @return Map<String, String>
     * @throws Exception
     */
    public Map<String, String> getSlots() throws Exception {
        byte[] jsonFile = Files.readAllBytes(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\slotsJson"));
        String slotsJson = new String(jsonFile);
        return apiUtil.generateMapFromJsonString(slotsJson);
    }

    /**
     * Method to update slots and user and return if its existing user
     * @param data
     * @return String
     * @throws Exception
     */
    public String updateSlotsAndUser(String data) throws Exception {
        byte[] jsonFile = Files.readAllBytes(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\slotsJson"));
        byte[] userSlotJsonFile = Files.readAllBytes(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\userSlotJson"));
        byte[] userJsonFile = Files.readAllBytes(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\userJson"));
        String slotsJson = new String(jsonFile);
        Map<String, String> slotMap = apiUtil.generateMapFromJsonString(slotsJson);
        Map<String, String> requestMap = apiUtil.generateMapFromJsonString(data);
        List userSlotJson = apiUtil.generateListFromJsonString(new String(userSlotJsonFile));
        List userJson = apiUtil.generateListFromJsonString(new String(userJsonFile));
        Map<String, String> userSlotMap = new HashMap<>();
        userSlotMap.put("email", requestMap.get("email").toLowerCase());
        if (!userJson.contains(userSlotMap)) {
            userSlotMap.put("slot", requestMap.get("slot"));
            userSlotJson.add(userSlotMap);
            requestMap.put("existingUser", "false");
            int slots = Integer.parseInt(slotMap.get(requestMap.get("slot")));
            slotMap.put(requestMap.get("slot"), slots + 1 + "");
            Files.write(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\slotsJson"), apiUtil.mapToJson(slotMap).getBytes());
            Files.write(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\userSlotJson"), apiUtil.listToJson(userSlotJson).getBytes());
            userSlotMap.remove("slot");
            userJson.add(userSlotMap);
            Files.write(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\userJson"), apiUtil.listToJson(userJson).getBytes());
        } else {
            requestMap.put("existingUser", "true");
        }
        return apiUtil.mapToJson(requestMap);
    }

    /**
     * Method to delete user slot
     * @param data
     * @return String
     * @throws Exception
     */
    public String deleteUserSlot(String data) throws Exception {
        byte[] jsonFile = Files.readAllBytes(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\slotsJson"));
        byte[] userSlotJsonFile = Files.readAllBytes(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\userSlotJson"));
        byte[] userJsonFile = Files.readAllBytes(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\userJson"));
        String slotsJson = new String(jsonFile);
        Map<String, String> slotMap = apiUtil.generateMapFromJsonString(slotsJson);
        Map<String, String> requestMap = apiUtil.generateMapFromJsonString(data);
        List<Map<String,Object>> userSlotJson = apiUtil.generateListFromJsonString(new String(userSlotJsonFile));
        List userJson = apiUtil.generateListFromJsonString(new String(userJsonFile));
        Map<String, String> userSlotMap = new HashMap<>();
        for (Map map: userSlotJson) {
            if (map.containsKey("email") && map.get("email").equals(requestMap.get("email").toLowerCase())) {
                userSlotMap.putAll(map);
                break;
            }
        }
        if(userSlotJson.contains(userSlotMap) && userSlotMap.containsKey("slot")) {
            requestMap.put("slot", userSlotMap.get("slot"));
            userSlotJson.remove(userSlotMap);
            int slots = Integer.parseInt(slotMap.get(userSlotMap.get("slot")));
            slotMap.put(userSlotMap.get("slot"), slots - 1 + "");
            Files.write(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\slotsJson"), apiUtil.mapToJson(slotMap).getBytes());
            Files.write(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\userSlotJson"), apiUtil.listToJson((List) userSlotJson).getBytes());
            userSlotMap.remove("slot");
            userJson.remove(userSlotMap);
            Files.write(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\userJson"), apiUtil.listToJson(userJson).getBytes());
        }
        return apiUtil.mapToJson(requestMap);
    }

    /**
     * Method to get adopt pup details json
     * @return List<Map<String, Object>>
     * @throws Exception
     */
    public List<Map<String, Object>> getAdoptPupDetails() throws Exception {
        byte[] jsonFile = Files.readAllBytes(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\adoptDogDetailsJson"));
        String slotsJson = new String(jsonFile);
        return apiUtil.generateListFromJsonString(slotsJson);
    }

    /**
     * Method to get shopping details json
     * @return List<Map<String, Object>>
     * @throws Exception
     */
    public List<Map<String, Object>> getShoppingDetails() throws Exception {
        byte[] jsonFile = Files.readAllBytes(Paths.get("C:\\Varsh\\workspace\\spring-dogadoption-restapi\\api\\src\\main\\resources\\shoppingDetailsJson"));
        String slotsJson = new String(jsonFile);
        return apiUtil.generateListFromJsonString(slotsJson);
    }
}
