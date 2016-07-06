package com.gaohuan.pattern.lod.principle;

/**
 * Created by acer on 2016/7/6.
 */
public class Button {
    private Mediator mediator;

    public void onClick() {
        mediator.action();
    }
}
