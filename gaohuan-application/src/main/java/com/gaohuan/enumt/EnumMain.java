package com.gaohuan.enumt;

/**
 * Created by gh on 2015/12/22.
 */
public class EnumMain {

    public static void main(String[] args) {
        System.out.println( Enum.ONE.toString());
        Enum[] ena = Enum.values();
        System.out.println(ena[0]);

    }
}
