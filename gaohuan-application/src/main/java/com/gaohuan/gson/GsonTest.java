package com.gaohuan.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by gh on 2016/2/26 0026.
 */
public class GsonTest {

    public static void main(String[] args) {
        Gson gson = new Gson();
        println(gson.toJson(1));

        println(gson.toJson("abcd"));
        println(gson.toJson(new Long(10)));
        int[] values = {1};
        println(gson.toJson(values));


        int one = gson.fromJson("1", int.class);
        println(one);

        Integer iOne = gson.fromJson("1", Integer.class);
        println(iOne);

        Long lOne = gson.fromJson("1", Long.class);
        println(lOne);

        Boolean aFalse = gson.fromJson("false", Boolean.class);
        println(aFalse);

        String str = gson.fromJson("\"abc\"", String.class);
        println(str);

        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);

        Collection<Integer> ints = Collections.unmodifiableList(Arrays.asList(1, 2, 3, 4, 5));
        String json = gson.toJson(ints);
        System.out.println(json);

        Type cType = new TypeToken<Collection<Integer>>() {
        }.getType();
        Collection<Integer> ints2 = gson.fromJson(json, cType);
        for (Integer i : ints2) {
            System.out.println(i);
        }

        gson = new GsonBuilder().serializeNulls().create();
        Foo foo = new Foo();
        json = gson.toJson(foo);
        System.out.println(json);
        json = gson.toJson(null);
        System.out.println(json);
    }

    static class Foo {
        private final String s;
        private final int i;

        public Foo(String s, int i) {
            this.s = s;
            this.i = i;
        }

        public Foo() {
            this(null, 5);
        }
    }

    public static void println(Object value) {
        System.out.println(value);
    }


}
