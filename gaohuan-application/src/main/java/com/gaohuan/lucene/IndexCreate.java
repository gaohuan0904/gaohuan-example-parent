package com.gaohuan.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

/**
 * Created by acer on 2016/7/5.
 */
public class IndexCreate {

    public static void main(String[] args) {
        //分词器
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
        //indexWriter
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_43, analyzer);
        //索引打开方式，没有就新建，有就打开
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        Directory directory = null;
        IndexWriter indexWriter = null;
        try {
            directory = FSDirectory.open(new File("D://lucene/index/"));
            //如果索引处于锁定状态，则解锁
            if (IndexWriter.isLocked(directory)) {
                IndexWriter.unlock(directory);
            }
            //指定索引操作对象
            indexWriter = new IndexWriter(directory, indexWriterConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //创建文档
        Document doc1 = new Document();
        doc1.add(new TextField("name", "测试标题", Field.Store.YES));
        doc1.add(new TextField("content", "测试内容", Field.Store.YES));
        //文档写入索引
        try {
            indexWriter.addDocument(doc1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //创建文档二
        Document doc2 = new Document();
        doc2.add(new TextField("name", "基于lucene的案例开发：索引数学模型", Field.Store.YES));
        doc2.add(new TextField("content", "lucene将一篇文档分成若干个域，每个域又分成若干个词元，通过词元在文档中的重要程度，将文档转化为N维的空间向量，通过计算两个向量之间的夹角余弦值来计算两个文档的相似程度", Field.Store.YES));
        try {
            //将文档写入到索引中
            indexWriter.addDocument(doc2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //提交操作
            indexWriter.commit();
            //关闭资源
            indexWriter.close();
            directory.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
