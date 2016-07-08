package com.gaohuan.pattern.afp;

/**
 * Created by acer on 2016/7/7.
 */
public class Client {

    public static void main(String[] args) {
        SkinFactory factory = new SpringSkinFactory();
        factory.createButton().display();
        factory.createTextField().display();
        factory.createComboBox().display();
    }
}
