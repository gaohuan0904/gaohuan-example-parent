package com.gaohuan.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by gh on 2015/12/2.
 */
public class FreeMakerUtils {

    public static final String TEMPLATE_PATH = "/freemarker";
    public static final String TEMPLATE_MAPPER_FILE = "mapper.ftl";
    public static final String TEMPLATE_JAVA_MAPPER_FILE = "javaMapper.ftl";
    public static final String TEMPLATE_SERVICE_FILE = "service.ftl";
    public static final String TEMPLATE_SERVICEIMPL_FILE = "serviceImpl.ftl";
    private static Configuration configuration;

    static {
        configuration = new Configuration(Configuration.VERSION_2_3_22);
        configuration.setClassForTemplateLoading(FreeMakerUtils.class.getClass(), TEMPLATE_PATH);
        configuration.setDefaultEncoding("utf-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public static Template getTemplate(String templateName) throws IOException {
        if (templateName == null) return null;
        return configuration.getTemplate(templateName);
    }

    public static void process(Object model, String templateName, String targetPath) throws IOException, TemplateException {
        getTemplate(templateName).process(model, new PrintWriter(targetPath));

    }


}
