package com.gaohuan.pay;

import java.math.BigDecimal;

/**
 * <p>User: acer
 * <p>Date: 2016/7/27
 * <p>Version: 1.0
 */
public class OrderVo {
    private String payType;
    private String orderNo;
    private BigDecimal price;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
