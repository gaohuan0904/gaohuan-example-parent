package com.gaohuan.pattern.observer.o2;

/**
 *  观察者模式
 *
 * 建立一种对象与对象之间的依赖关系，一个对象发生改变时将自动通知其他对象，其他对象相应作出反应。
 * 对象行为型模式
 * 别名：发布订阅模式 模型-试图模式 资源-监听器模式
 *
 * 凡是涉及一对一或者一对多的对象交互场景都可以使用观察者模式
 *
 * 优点：
 * 观察者模式建立了一种抽象耦合，观察目标只需要维护了一个抽象的观察者集合，属于不同的抽象层次。
 * 满足开闭原则，在观察者和观察目标不存在关联关系的情况下，增加新的观察者很方便，
 *
 * 缺点：
 * 如果观察者或间接观察者太多，通知到所有观察者会花很多时间
 * 如果观察者与观察目标之间存在循环依赖，可能导致系统崩溃
 * 观察者没有相应的机制让观察者知道目标对象的状态是怎么发生变化，仅仅知道对象发生了改变。
 *
 * 使用场景：
 * 一个抽象模型中有两个方便，其中一个方面依赖另一个方面，可以将他们封装在独立的对象中进行改变和服用
 * 一个对象的改变导致一个或多个对象发生改变，而不知道具体有多少个对象发生改变，也不知道这些对象是谁。
 * 创建一个触发链，A对象的行为影响B对象，B对象的行为影响C对象。
 *
 *
 * <p>User: acer
 * <p>Date: 2016/7/29
 * <p>Version: 1.0
 */
public class Application {
    private ApplicationEventMulticaster multicaster;


    private static class ApplicationHolder {
        private final static Application application = new Application();

    }

    public void init() {
        multicaster = new ApplicationEventMulticasterImpl();
        multicaster.addApplicationListener(new ApplicationListenerImpl());

    }

    public static Application create() {
        return ApplicationHolder.application;
    }

    public static void main(String[] args) {
        Application application = Application.create();
        application.init();
        application.publishEvent(new ApplicationEvent(null));
    }

    private void publishEvent(ApplicationEvent applicationEvent) {
        multicaster.multicastEvent(applicationEvent);
    }


}
