package com.gaohuan.pattern.op.principle;

/**
 * 使用开闭原则
 */
public class CharDisplay {
    private AbstractChart chart;

    public CharDisplay(AbstractChart chart) {
        this.chart = chart;
    }

    public void display() {
        chart.display();
    }
}
