package com.gaohuan.pattern.sfp;

/**
 * Created by acer on 2016/7/6.
 */
public class Chart {
    private String type;

    public Chart(Object[][] data, String type) {
        this.type = type;
        if (type.equalsIgnoreCase("histogram")) {
            //柱状图
        } else if (type.equalsIgnoreCase("pie")) {
            //饼状图
        } else if (type.equalsIgnoreCase("line")) {
            //折线图
        }
    }

    public void display() {
        if (type.equalsIgnoreCase("histogram")) {
            //柱状图
        } else if (type.equalsIgnoreCase("pie")) {
            //饼状图
        } else if (type.equalsIgnoreCase("line")) {
            //折线图
        }
    }
}
