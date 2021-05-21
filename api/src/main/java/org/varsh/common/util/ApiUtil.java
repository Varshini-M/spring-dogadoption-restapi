package org.varsh.common.util;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ApiUtil {

    public ResponseEntity<Map<String, String>> constructError(String errorCode, Exception e){
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("errorCode",errorCode);
        errorResponse.put("errorMessage",e.getLocalizedMessage());
        return ResponseEntity.status(4001).body(errorResponse);
    }

    public String mapToJson(Map<String,String> map){
        return replaceLast(escapeCharacters(map.toString()).replaceAll("=","\":\"")
                .replaceAll(", ","\",\"")
                .replaceFirst("\\{","\\{\""),"\\}","\"\\}");
    }

    public String listToJson(List<Map<String,String>> list){
        String value = "";
        List<String> jsonList = new ArrayList<>();
        for(Map<String,String> map:list){
            jsonList.add(mapToJson(map));
        }
        value = "["+String.join(",",jsonList)+"]";
        return value;
    }

    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }

    public static String escapeCharacters(String text){
        return text.replaceAll("\"","\\\\\"");
    }
}
