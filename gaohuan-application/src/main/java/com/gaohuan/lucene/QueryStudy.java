package com.gaohuan.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.util.Version;

/**
 * Created by acer on 2016/7/5.
 */
public class QueryStudy {
    public static void main(String[] args) throws Exception {
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
        //搜索词
        String key = "基于lucene的案例开发";
        //单个搜索域
        String field = "content";
        //多个搜索域
        String[] fields = {"name", "content"};
        //查询
        Query query = null;
        QueryParser parser = new QueryParser(Version.LUCENE_43, field, analyzer);
        query = parser.parse(key);
        System.out.println("QueryParser:");
        System.out.println(query.toString());
        //多域搜索
        MultiFieldQueryParser mParser = new MultiFieldQueryParser(Version.LUCENE_43, fields, analyzer);
        query = mParser.parse(key);
        System.out.println("MultiFieldQueryParser:");
        System.out.println(query.toString());
        //词条搜索
        query = new TermQuery(new Term(field, key));
        System.out.println("TermQuery:");
        System.out.println(query.toString());

        //前缀搜索
        query = new PrefixQuery(new Term(field, key));
        System.out.println("PrefixQuery");
        System.out.println(query.toString());

        //短语搜索
        PhraseQuery phraseQuery = new PhraseQuery();
        phraseQuery.setSlop(2);
        phraseQuery.add(new Term("content", "基于"));
        phraseQuery.add(new Term("content", "案例"));
        System.out.println("PhraseQuery:");
        System.out.println(phraseQuery.toString());

        //通配符搜索
        query = new WildcardQuery(new Term(field, "基于?"));
        System.out.println("WildcardQuery:");
        System.out.println(query.toString());

        //字符串范围搜索
        query = TermRangeQuery.newStringRange(field, "abc", "azz", true, false);
        System.out.println("TermRangeQuery:");
        System.out.println(query.toString());

        //int范围搜索
        query = NumericRangeQuery.newIntRange(field, 0, 3, false, false);
        System.out.println("NumericRangeQuery");
        System.out.println(query.toString());

        //double范围搜索
        query = NumericRangeQuery.newDoubleRange(field, 0.0, 3d, false, false);
        System.out.println("NumericRangeQuery");
        System.out.println(query.toString());

        //boolean query
        BooleanQuery booleanQuery = new BooleanQuery();
        booleanQuery.add(new TermQuery(new Term("content", "基于")), BooleanClause.Occur.SHOULD);
        booleanQuery.add(new TermQuery(new Term("name", "lucene")), BooleanClause.Occur.MUST);
        booleanQuery.add(new TermQuery(new Term("star", "3")), BooleanClause.Occur.MUST_NOT);
        System.out.println("BooleanQuery:");
        System.out.println(booleanQuery.toString());

    }
}
