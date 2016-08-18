package com.gaohuan.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

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

    }
}
