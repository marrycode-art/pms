package com.hui.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StringUtils {

    public static String parseparamrterMapToString(Map<String, Object> paramrterMap) {
        Set<Map.Entry<String, Object>> entries = paramrterMap.entrySet();
        String str = "";
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            str = str + "&" +"search_" + key + "=" + value;
        }
        return str;
    }

    public static Map<String, String> parseParameterMapToMyBatisMap(Map<String, Object> paramrterMap) {
        Map<String, String> resultMap = new HashMap<String, String>();
        Set<Map.Entry<String, Object>> entries = paramrterMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            String value = (String) entry.getValue();

            if (key.contains("like")) {
                key = key.substring(key.indexOf("_")+1);
                value = "%" + value + "%";
            }
            resultMap.put(key, value);
        }
        return resultMap;
    }
}
