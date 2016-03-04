package com.gaohuan.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huan on 2015/12/30.
 */
public class MainTest {
    public static void main(String[] args) {

        Map<String,String> map = new HashMap<>();
        map.put("gh", "gaohuan");
        map.put("sm", "shimeng");

        StringBuilder sb  = new StringBuilder();
        for(String key: map.keySet()){
            sb.append(key).append("=").append("\"").append(map.get(key)).append("\"").append("&");
        }
        System.out.println(sb.subSequence(0,sb.length()-1));

    }
}
