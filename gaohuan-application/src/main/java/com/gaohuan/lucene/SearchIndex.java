package com.gaohuan.lucene;

import com.gaohuan.log.LogUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;

/**
 * Created by acer on 2016/7/5.
 */
public class SearchIndex {
    public static final Logger logger = Logger.getLogger(SearchIndex.class);

    public static void main(String[] args) {
        Directory directory = null;
        try {
            directory = FSDirectory.open(new File("D://lucene/index/"));
            //读取索引
            DirectoryReader reader = DirectoryReader.open(directory);
            //创建检索对象
            IndexSearcher searcher = new IndexSearcher(reader);
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
            //创建查询query 搜索词"空间向量"
            QueryParser parser = new QueryParser(Version.LUCENE_43, "content", analyzer);
            Query query = parser.parse("空间向量");
            //检索索引，获取前10条记录
            TopDocs topDocs = searcher.search(query, 10);
            if (topDocs != null) {
                logger.debug(String.format("totalHits:%d", topDocs.totalHits));
                for (int i = 0; i < topDocs.scoreDocs.length; i++) {
                    Document document = searcher.doc(topDocs.scoreDocs[i].doc);
                    logger.debug(String.format("name:%s", document.get("name")));
                    logger.debug(String.format("content:%s", document.get("content")));
                }
            }
            //关闭资源
            reader.close();
            directory.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
