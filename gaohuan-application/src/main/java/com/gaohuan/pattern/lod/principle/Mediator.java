package com.gaohuan.pattern.lod.principle;

/**
 * Created by acer on 2016/7/6.
 */
public class Mediator {
    private List list;
    private Label label;
    private ComboBox comboBox;
    private TextBox textBox;

    public void action() {
        list.action();
        label.action();
        comboBox.action();
        textBox.action();
    }
}
