package com.gaohuan.pay;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>User: acer
 * <p>Date: 2016/7/27
 * <p>Version: 1.0
 */
public class PayHelper {

    public static final String WX_TYPE_NAME = "com.gaohuan.pay.WxPay";
    public static final String ALI_TYPE_NAME = "com.gaohuan.pay.AliPay";

    public enum PayType {
        WxPay(WX_TYPE_NAME), AliPay(ALI_TYPE_NAME);
        private String typeName;

        PayType(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    public static final String wxPay = "WxPay";

    public static final String aliPay = "AliPay";

    public static final Map<String, PayHandler> TYPE_TO_HANDLER = new HashMap();

    static {
        TYPE_TO_HANDLER.put(wxPay, new WxPay());
        TYPE_TO_HANDLER.put(aliPay, new AliPay());
    }


    public static String getTypeName(String payType) {
        return PayType.valueOf(payType).getTypeName();
    }

}
