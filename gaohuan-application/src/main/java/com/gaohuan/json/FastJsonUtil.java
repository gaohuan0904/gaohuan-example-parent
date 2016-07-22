package com.gaohuan.json;


import com.alibaba.fastjson.JSON;
import java.util.HashMap;

/**
 * Created by acer on 2016/7/5.
 */
public class FastJsonUtil {
    public static void main(String[] args) {
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        hash.put("key1", 1);
        hash.put("key2", 2);
        hash.put("key3", 3);
        System.out.println(JSON.toJSONString(hash));
    }
}
