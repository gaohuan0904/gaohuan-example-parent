package com.gaohuan.pattern.observer.o1;

/**
 * <p>User: acer
 * <p>Date: 2016/7/29
 * <p>Version: 1.0
 */
public class ObserverMain {

    public static void main(String[] args) {

        Subject subject = new SubjectImpl();//新建主题
        Observer observer1 = new ObserverImpl("observer1");//新建观察者
        Observer observer2 = new ObserverImpl("observer2");//新建观察者
        //注册观察者
        subject.addObserver(observer1);
        subject.addObserver(observer2);

        Event event = new Event();
        //改变主题状态,通知观察者
        subject.pushEvent(event);

        //移除观察者
        subject.removeObserver(observer2);
        Event newEvent = new Event();
        subject.pushEvent(newEvent);
    }

}
