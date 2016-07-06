package com.gaohuan.pattern.op;

/**
 * 未使用开闭原则
 */
public class CharDisplay {
    private String type;

    public CharDisplay(String type) {
        this.type = type;
    }

    public void display() {
        if (type.equals("pie")) {
            new PieChart().disPlay();
        } else if (type.equals("bar")) {
            new BarChart().disPlay();
        }
    }
}
