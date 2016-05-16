package com.gaohuan.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by gh on 2016/4/29 0029.
 */
public class CustomClassLoader extends ClassLoader {

    public static final Logger logger = LoggerFactory.getLogger(CustomClassLoader.class);


    public Class defineClass(String enhancedClassName, byte[] byteCode) {
        if (logger.isDebugEnabled()) {
            logger.debug("defineClass(" + enhancedClassName + ")");
        }
        return this.defineClass(enhancedClassName, byteCode, 0, byteCode.length);
    }

    public InputStream getResourceAsStream(String name) {
        URL url = getResource(name);
        try {
            return url != null ? url.openStream() : null;
        } catch (IOException e) {
            return null;
        }
    }
}
