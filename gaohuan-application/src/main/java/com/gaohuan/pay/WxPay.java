package com.gaohuan.pay;

/**
 * <p>User: acer
 * <p>Date: 2016/7/27
 * <p>Version: 1.0
 */
public class WxPay implements PayHandler {

    @Override
    public PayResult doPayHandle(String orderNo) {
        return null;
    }

    @Override
    public PreResult preOrderHandle(OrderVo orderVo) {
        return null;
    }
}
