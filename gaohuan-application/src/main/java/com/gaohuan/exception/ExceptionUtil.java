package com.gaohuan.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by gh on 2016/1/12.
 */
public class ExceptionUtil {

    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }


}
