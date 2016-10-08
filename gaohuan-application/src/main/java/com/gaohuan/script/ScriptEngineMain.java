package com.gaohuan.script;


import javax.script.*;
import java.util.Date;
import java.util.List;

/**
 * Created by g.h on 2016/9/30.
 */
public class ScriptEngineMain {
    public static final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();

    public static void main(String[] args) {
        test7();
    }

    public static void test1() {
        List<ScriptEngineFactory> factoryList = scriptEngineManager.getEngineFactories();
        factoryList.forEach((engineFactory) -> {
            String name = engineFactory.getEngineName();
            String version = engineFactory.getEngineVersion();
            String languageName = engineFactory.getLanguageName();
            System.out.println(engineFactory.getMimeTypes());
            StringBuilder out = new StringBuilder();
            out.append("{")
                    .append("name:").append(name).append("\n")
                    .append("version:").append(version).append("\n")
                    .append("languageName:").append(languageName).append("\n").append("}");
            System.out.println(out.toString());
        });
    }

    public static void test2() {
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
        try {
            String script = "var date = new Date();" + "date.getHours();";
            Double hour = (Double) scriptEngine.eval(script);
            System.out.println(hour);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    /**
     * 和脚本交互
     */
    public static void test3() {
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
        try {
            scriptEngine.put("name", "abcdef");
            String script = "var output = '';\n" +
                    "for (var i = 0; i < name.length; i++) {\n" +
                    "    output = name.charAt(i) + output;\n" +
                    "}";

            scriptEngine.eval(script);
            String name = (String) scriptEngine.get("output");
            System.out.println(name);
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }

    /**
     * 编译运行
     */
    public static void test4() {
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
        try {
            scriptEngine.put("counter", 0);
            if (scriptEngine instanceof Compilable) {
                Compilable compilableEngine = (Compilable) scriptEngine;
                String script = "function count() {\n" +
                        "            counter = counter + 1;\n" +
                        "            return counter;\n" +
                        "         }\n" +
                        "         count();";
                CompiledScript compiledScript = compilableEngine.compile(script);
                System.out.println(compiledScript.eval());
                System.out.println(compiledScript.eval());
                System.out.println(compiledScript.eval());
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    /**
     * 动态调用
     */
    public static void test5() {
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
        try {
            if (scriptEngine instanceof Invocable) {
                String name = "abcdef";
                String script = "function reverse(name) {\n" +
                        "    var output = '';\n" +
                        "    for (var i = 0; i < name.length; i++) {\n" +
                        "        output = name.charAt(i) + output;\n" +
                        "    }\n" +
                        "    return output;\n" +
                        "\n" +
                        "}";
                scriptEngine.eval(script);
                Invocable invocable = (Invocable) scriptEngine;
                Object result = invocable.invokeFunction("reverse", name);
                System.out.println(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步执行
     */
    public static void test6() {
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
        try {
            scriptEngine.eval("function run() {\n" +
                    "    print(\"异步调用\");\n" +
                    "\n" +
                    "}");
            Invocable invocable = (Invocable) scriptEngine;
            Runnable runnable = invocable.getInterface(Runnable.class);
            Thread t = new Thread(runnable);
            t.start();
            t.join();
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void test7() {
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("groovy");
        scriptEngine.put("date", new Date());
        try {
            scriptEngine.eval("def getTime() { return date.getTime(); }");
            scriptEngine.eval("def sayHello(name, age) { return 'Hello,I am ' + name + ',age' + age; }");
            Object time = ((Invocable) scriptEngine).invokeFunction("getTime", null);
            System.out.println("time:" + time);
            Object message = ((Invocable) scriptEngine).invokeFunction("sayHello", "zhangsan", 12);
            System.out.println(message);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
