package com.gaohuan.pattern.sfp.principle;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by acer on 2016/7/6.
 */
public class XmlUtil {
    public static String readChartType() {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document;
            String path = XmlUtil.class.getResource("").getPath() + "config.xml";
            document = builder.parse(new File(path));
            NodeList nl = document.getElementsByTagName("chartType");
            Node node = nl.item(0).getFirstChild();
            String chartType = node.getNodeValue().trim();
            return chartType;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
