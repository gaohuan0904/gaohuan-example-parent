package com.gaohuan.pattern.lod;

/**
 * Created by acer on 2016/7/6.
 */
public class Button {
    private List list;
    private Label label;
    private ComboBox comboBox;
    private TextBox textBox;

    public void onClick() {
        list.action();
        label.action();
        comboBox.action();
        textBox.action();
    }
}
