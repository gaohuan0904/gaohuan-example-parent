package com.gaohuan.pattern.afp;

/**
 * Created by acer on 2016/7/7.
 */
public interface SkinFactory {
    public Button createButton();

    public TextField createTextField();

    public ComboBox createComboBox();
}
