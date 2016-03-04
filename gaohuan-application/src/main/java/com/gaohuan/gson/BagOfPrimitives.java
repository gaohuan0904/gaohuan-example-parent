package com.gaohuan.gson;

import com.google.gson.Gson;

/**
 * Created by gh on 2016/2/26 0026.
 */
public class BagOfPrimitives {

    private int value1 = 1;
    private String value2 = "abc";
    private transient int value3 = 3;

    public BagOfPrimitives() {
    }

    @Override
    public String toString() {
        return "BagOfPrimitives{" +
                "value1=" + value1 +
                ", value2='" + value2 + '\'' +
                ", value3=" + value3 +
                '}';
    }

    public static void main(String[] args) {
        BagOfPrimitives obj = new BagOfPrimitives();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        System.out.println(json);
        BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);
        System.out.println(obj2);
        System.out.println(obj);
    }
}
