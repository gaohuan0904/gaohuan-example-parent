package com.gaohuan.code.generator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gh on 2015/12/4.
 */
public class TypeConvertUtil {

    //映射类型
    public static final Map<String,String> MAPPING_TYPE_MAP = new HashMap<>();
    //完全类型
    public static final Map<String,String> FULL_TYPE_MAP = new HashMap();

    static {
        MAPPING_TYPE_MAP .put("varchar","String");
        MAPPING_TYPE_MAP .put("datetime","Date");
        MAPPING_TYPE_MAP .put("tinyinttinyint","Integer");
        MAPPING_TYPE_MAP .put("int","Integer");
        MAPPING_TYPE_MAP .put("bigint","Long");
        MAPPING_TYPE_MAP .put("decimal","BigDecimal");

        FULL_TYPE_MAP.put("Date","java.util.Date");
        FULL_TYPE_MAP.put("BigDecimal","java.math.BigDecimal");
    }

}
