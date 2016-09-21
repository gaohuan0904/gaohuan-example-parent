package com.gaohuan.reference;

import java.lang.ref.*;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/18
 */
public class References {
    public static void main(String[] args) {
        Object weakObj, phantomObj;
        Reference ref;
        WeakReference weakRef;
        PhantomReference phantomRef;
        ReferenceQueue weakQueue, phantomQueue;
        SoftReference softRef;
        /*
        weakObj = new String("Weak Reference");
        phantomObj = new String("Phantom Reference");
        weakQueue = new ReferenceQueue();
        phantomQueue = new ReferenceQueue();
        weakRef = new WeakReference(weakObj, weakQueue);
        phantomRef = new PhantomReference(phantomObj, phantomQueue);

        System.out.println("Weak Reference:" + weakRef.get());
        System.out.println("Phantom Reference:" + phantomRef.get());

        //clear all strong references
        weakObj = null;
        phantomObj = null;
        System.gc();
        System.out.println("weak queued:" + weakRef.isEnqueued());
        if (!phantomRef.isEnqueued()) {
            System.out.println("requestion finalization");
            System.runFinalization();
        }
        System.out.println("Pantom Queued:" + phantomRef.isEnqueued());

        try {
            ref = weakQueue.remove();
            System.out.println("Weak Reference:" + ref.get());
            ref = phantomQueue.remove();
            System.out.println("Phantom Reference:" + ref.get());
            ref.clear();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        /*
        System.out.println("--------------------------------");
        weakObj = new Object();
        weakQueue = new ReferenceQueue();
        weakRef = new WeakReference(weakObj, weakQueue);
        weakObj = null;//help gc
        System.out.println("before gc:" + weakRef.get());
        System.gc();
        System.out.println("after gc:" + weakRef.get());
        */

    }
}
