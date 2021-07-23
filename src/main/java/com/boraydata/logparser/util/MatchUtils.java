package com.boraydata.logparser.util;

import java.util.HashMap;
import java.util.Map;

/** 取代  jackson  的功能  手动将 Map 输出为 Json
 * @author bufan
 * @data 2021/7/23
 */
public class MatchUtils {
    public static String mapToJson(Map<String, Object> map){

        StringBuilder json = new StringBuilder();

        for (Map.Entry<String,Object> entry : map.entrySet()) {
            json.append(String.format("\"%s\":\"%s\",", entry.getKey(), entry.getValue() == null ? "" : entry.getValue()));
        }
        int length = json.length()-1;
        if (json.codePointAt(length)==44)
            json.deleteCharAt(length);

        return String.valueOf("{"+json+"}");
    }


}
