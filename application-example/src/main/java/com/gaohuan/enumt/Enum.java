package com.gaohuan.enumt;

/**
 * Created by gh on 2015/12/22.
 */
public enum Enum {
    ONE(1), TWO(2);
    private int i;

    Enum(int i) {
        this.i = i;
    }

//    @Override
//    public String toString() {
//        return String.valueOf(i);
//    }
}
