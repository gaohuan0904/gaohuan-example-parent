package com.gaohuan.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * Created by huan on 2015/11/19.
 */
public class SqlSessionFactoryUtil {
    private static final Logger logger = LoggerFactory.getLogger(SqlSessionFactoryUtil.class);
    private static SqlSessionFactory sqlSessionFactory;

    static {
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("未加载到配置文件，系统退出！");
            System.exit(1);
        }
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
