package com.gaohuan.pay;

/**
 * 支付处理类
 * <p>User: acer
 * <p>Date: 2016/7/27
 * <p>Version: 1.0
 */
public interface PayHandler {
    /**
     * 支付回调业务
     *
     * @param orderNo
     * @return
     */
    public PayResult doPayHandle(String orderNo);

    /**
     * 预订单生处理业务
     *
     * @param orderVo
     * @return
     */
    public PreResult preOrderHandle(OrderVo orderVo);
}
