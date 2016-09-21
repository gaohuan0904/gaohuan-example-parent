package com.gaohuan.java8.annotations;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/12
 */
class TypeAnnoDemo<@What(description = "generic data type") T> {

    public @Unique TypeAnnoDemo() {
    }

    @TypeAnno String str;

    @EmptyOk
    String test;

    public int f(@TypeAnno TypeAnnoDemo<T>this, int x) {
        return 10;
    }

    public @TypeAnno Integer f2(int j, int k) {
        return j + k;
    }

    public
    @Recommended
    Integer f3(String str) {
        return str.length() / 2;
    }

    public void f4() throws @TypeAnno NullPointerException {

    }

    String @MaxLen(10) [] @NotZeroLen [] w;

    @TypeAnno Integer[] vec;

    public static void myMeth(int i) {
        TypeAnnoDemo<@TypeAnno Integer> ob = new TypeAnnoDemo<@TypeAnno Integer>();
        @Unique TypeAnnoDemo<Integer> obj2 = new @Unique TypeAnnoDemo<Integer>();
        Object x = new Integer(10);
        Integer y;
        y = (@TypeAnno Integer) x;

    }

    public static void main(String[] args) {
        myMeth(10);
    }

    class SomeClass extends @TypeAnno TypeAnnoDemo<Boolean> {
    }
}
