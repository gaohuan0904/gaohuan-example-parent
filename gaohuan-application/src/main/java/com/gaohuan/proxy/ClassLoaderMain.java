package com.gaohuan.proxy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by acer on 2016/7/22.
 */
public class ClassLoaderMain {
    public static void main(String[] args) {
        loadSubTypes(People.class);
    }

    public static List<Class> loadSubTypes(Class c) {
        List<Class> retList = new ArrayList<Class>();
        if (c.isInterface()) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Class ofClassLoader = classLoader.getClass();
            while (ClassLoader.class != ofClassLoader) {
                ofClassLoader = ofClassLoader.getSuperclass();
            }
            try {
                Field field = ofClassLoader.getDeclaredField("classes");
                field.setAccessible(true);
                Vector vector = (Vector) field.get(classLoader);
                for (int i = 0; i < vector.size(); i++) {
                    Class cc = (Class) vector.get(i);
                    System.out.println(cc.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retList;
    }
}
