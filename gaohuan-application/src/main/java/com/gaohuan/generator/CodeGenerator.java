package com.gaohuan.generator;

import freemarker.template.TemplateException;
import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by gh on 2015/12/2.
 */
public class CodeGenerator {

    static {
        resourceBundle = ResourceBundle.getBundle(CodeGenerator.RESOURCE_NAME);
    }

    public static ResourceBundle resourceBundle;
    public static final String RESOURCE_NAME = "generateCodeConfig";
    public static final String BASEPACKAGE_KEY_NAME = "com.generate.basePackage";
    public static final String DOMAINNAME_KEY_NAME = "com.generate.domainName";
    public static final String TARGETPATH_KEY_NAME = "com.generate.targetPath";
    public static final String MAPPER_PATH_KEY_NAME = "com.generate.mapper.basePackage";

//    private static final String domainName = getStringByKey(DOMAINNAME_KEY_NAME);
//    private static final String basePackage = getStringByKey(BASEPACKAGE_KEY_NAME);
    private static final String targetPath = getStringByKey(TARGETPATH_KEY_NAME);


    public static void main(String[] args) throws Exception {
        List list = Arrays.asList(
                "hb_bank_card",
                "hb_feedback",
                "hb_func_setting",
                "hb_grab_red_enve",
                "hb_initial_img",
                "hb_news",
                "hb_news_img",
                "hb_rulemaking",
                "hb_send_red_enve",
                "hb_send_red_enve_date",
                "hb_sy_advert_img",
                "hy_account_detail",
                "hy_integral_detail",
                "hy_red_enve_detail",
                "t_member"
        );

        List<Map> models = DbUtil.makeModelMap(list);

        generateModel(models);

        generateMapper(models);

        generateService(models);

    }

    /**
     * 生成model
     *
     * @throws IOException
     */
    public static void generateModel(List<Map> models) throws IOException, TemplateException {
        if (CollectionUtils.isNotEmpty(models)) {
            for (Map model : models) {
                String subPath = "java" + File.separator;
                String fileName = model.get("className") + ".java";
                //生成文件
                FreeMakerUtils.process(model, FreeMakerUtils.TEMPLATE_JAVA_MAPPER_FILE, targetPath + subPath + fileName);
            }
        }

    }

    /**
     * 生成mapper
     *
     * @throws Exception
     */
    public static void generateMapper(List<Map> models) throws Exception {
        if (CollectionUtils.isNotEmpty(models)) {
            for (Map model : models) {
                String fileName = model.get("className") + "Mapper.xml";
                //生成文件
                FreeMakerUtils.process(model, FreeMakerUtils.TEMPLATE_MAPPER_FILE, targetPath + fileName);
            }
        }

    }

    public static void generateService(List<Map> models) throws IOException, TemplateException {

        if (CollectionUtils.isNotEmpty(models)) {
            for (Map model : models) {
                String subServicePath = "service" + File.separator;
                String subServiceImplPath = "service" + File.separator + "impl" + File.separator;
                String serviceName = model.get("className") + "Service.java";
                String serviceImplName = model.get("className") + "ServiceImpl.java";
                //生成文件
                FreeMakerUtils.process(model, FreeMakerUtils.TEMPLATE_SERVICE_FILE, targetPath + subServicePath + serviceName);
                FreeMakerUtils.process(model, FreeMakerUtils.TEMPLATE_SERVICEIMPL_FILE, targetPath + subServiceImplPath + serviceImplName);
            }
        }

    }


    /**
     * 获取属性
     *
     * @param key
     * @return
     */
    public static String getStringByKey(String key) {
        if (key == null) return null;
        return resourceBundle.getString(key) == null ? null : resourceBundle.getString(key);
    }

    /**
     * 创建文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static File createFile(File file) throws IOException {

        if (file.getParentFile() != null && file.getParentFile().exists()) {
            file.getParentFile().delete();
        }
        file.getParentFile().mkdirs();

        if (file.isFile() && file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }
}
