package com.gaohuan.xml;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 2016/7/5.
 */
public class XmlUtil {

    private static final String NO_RESULT = "<root>no result</root>";

    public static String parseObjectToXml(Object obj) {
        if (obj == null) {
            return NO_RESULT;
        }
        StringWriter sw = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(obj, sw);
            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return NO_RESULT;
    }

    @XmlRootElement(name = "root")
    public static class Xml {

        private String name;
        private String text;
        private List children;

        public Xml() {
            this.name = "test";
            this.text = "test";
            this.children = new ArrayList();
            children.add("test1");
            children.add("test2");
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List getChildren() {
            return children;
        }

        public void setChildren(List children) {
            this.children = children;
        }
    }

    public static void main(String[] args) {
        String xml = XmlUtil.parseObjectToXml(null);
        System.out.println(xml);
    }
}
