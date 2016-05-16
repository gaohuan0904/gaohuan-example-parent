package com.gaohuan.cache;

/**
 * Created by gh on 2016/4/8 0008.
 */
public class CacheElement {

    private Object objectValue;
    private Object objectKey;
    private int index;
    private int hitCount;

    public Object getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(Object objectValue) {
        this.objectValue = objectValue;
    }

    public Object getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(Object objectKey) {
        this.objectKey = objectKey;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }
}
