package com.gaohuan.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by acer on 2016/7/5.
 */
public class AnalyzerStudy {
    public static void main(String[] args) {
        String str = "这是一个分词器测试程序，希望大家继续关注我的个人系列博客：基于Lucene的案例开发，这里加一点带空格的标签 LUCENE java 分词器";
        Analyzer analyzer = null;
        //标准分词器
        analyzer = new StandardAnalyzer(Version.LUCENE_43);

        //处理字符串
        StringReader reader = new StringReader(str);
        try {
            TokenStream tokenStream = analyzer.tokenStream("", reader);
            tokenStream.reset();
            CharTermAttribute term = tokenStream.getAttribute(CharTermAttribute.class);
            int l = 0;
            //输出结果
            while (tokenStream.incrementToken()) {
                System.out.print(term.toString() + "|");
                l += term.toString().length();
                if (l > 30) {
                    System.out.println();
                    l = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

