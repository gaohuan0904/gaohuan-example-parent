package com.gaohuan.yml;


import org.apache.commons.lang.ObjectUtils;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;

/**
 * Created by gh on 2016/5/16 0016.
 */
public class Main {

    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        String document = "\n- Hesperiaaa\n- Papi\n- padfasd\n- Apate\n- Epoiple";
        List<String> list = (List<String>) yaml.load(document);
        printList(list);

        document = "hello:25";
        Map map = (Map) yaml.load(document);


    }

    public static void printList(List list) {
        for (int i = 0; i < list.size(); i++) {
            String str = ObjectUtils.toString(list.get(i));
            System.out.println(str);
        }
    }
}
