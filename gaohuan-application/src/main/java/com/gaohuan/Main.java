package com.gaohuan;

import org.apache.commons.lang.ArrayUtils;

/**
 * Created by gh on 2016/5/24 0024.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(Main.class.getResource("").getPath() + "config.xml");

    }

    public static boolean isNotEmpty(Object... params) {
        return ArrayUtils.isNotEmpty(params);
    }
}
