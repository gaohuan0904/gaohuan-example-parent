package com.gaohuan.pattern.observer.o2;

/**
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
