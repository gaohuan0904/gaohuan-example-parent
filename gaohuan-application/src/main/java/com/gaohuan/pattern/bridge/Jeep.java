package com.gaohuan.pattern.bridge;

/**
 * Abstraction子类
 */
public class Jeep extends Car {
    public Jeep(Engine engine) {
        super(engine);
    }

    @Override
    public void installEngine() {
        System.out.println("jeep:");
        this.getEngine().installEngine();
    }
}
