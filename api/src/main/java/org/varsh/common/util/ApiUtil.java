package org.varsh.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ApiUtil {

    /**
     * Method to set errorcode and error message for exceptions
     * @param errorCode
     * @param e
     * @return ResponseEntity<Map<String, String>>
     */
    public ResponseEntity<Map<String, String>> constructError(String errorCode, Exception e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("errorCode", errorCode);
        errorResponse.put("errorMessage", e.getLocalizedMessage());
        return ResponseEntity.status(4001).body(errorResponse);
    }

    /**
     * Method to convert map to json(String)
     * @param inputMap
     * @return String
     * @throws Exception
     */
    public static String mapToJson(Map<String, String> inputMap) throws Exception {
        ObjectMapper mapperObj = new ObjectMapper();
        String jsonResp = null;
        try {
            jsonResp = mapperObj.writeValueAsString(inputMap);
        } catch (IOException e) {
            throw e;
        }
        return jsonResp;
    }

    /**
     * Method to convert List<Map<String,String> to json(string)
     * @param list
     * @return String
     * @throws Exception
     */
    public String listToJson(List<Map<String, String>> list) throws Exception {
        String value = "";
        List<String> jsonList = new ArrayList<>();
        for (Map<String, String> map : list) {
            jsonList.add(mapToJson(map));
        }
        value = "[" + String.join(",", jsonList) + "]";
        return value;
    }

    /**
     * Method to generate list from json string
     * @param jsonString
     * @return List<Map<String, Object>>
     * @throws Exception
     */
    public static List<Map<String, Object>> generateListFromJsonString(String jsonString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> finalList = null;
        try {
            finalList = mapper.readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (IOException | NullPointerException e) {
            throw e;
        }
        return finalList;
    }

    /**
     * Method to generate Map from json string
     * @param jsonString
     * @return Map<String, String>
     * @throws Exception
     */
    public static Map<String, String> generateMapFromJsonString(String jsonString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> finalMap = null;
        try {
            finalMap = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException | NullPointerException e) {
            throw e;
        }
        return finalMap;
    }
}
