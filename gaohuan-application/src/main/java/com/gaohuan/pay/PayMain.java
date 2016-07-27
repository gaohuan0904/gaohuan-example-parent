package com.gaohuan.pay;

/**
 * 支付相关学习，封装基本结构，待补充实现类
 * <p>User: acer
 * <p>Date: 2016/7/27
 * <p>Version: 1.0
 */
public class PayMain {
    public static void main(String[] args) {

        String payType = PayHelper.wxPay;
        String orderNo = "S0000000000000";
        OrderVo orderVo = new OrderVo();
        orderVo.setPayType(payType);

        PayHandler payHandler = PayHelper.TYPE_TO_HANDLER.get(payType);
        //生成预订单
        PreResult preResult = payHandler.preOrderHandle(orderVo);
        //回调执行的方法
        PayResult payResult = payHandler.doPayHandle(orderNo);

    }
}
