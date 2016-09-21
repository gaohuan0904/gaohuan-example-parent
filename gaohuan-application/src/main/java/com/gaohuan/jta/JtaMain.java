package com.gaohuan.jta;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by acer on 2016/7/8.
 */
public class JtaMain {

    public static void main(String[] args) throws SystemException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:jta/spring-jta.xml");

        //jta demo
        AtomikosDataSourceBean dataSourceA = (AtomikosDataSourceBean) ctx.getBean("dataSourceA");
        AtomikosDataSourceBean dataSourceB = (AtomikosDataSourceBean) ctx.getBean("dataSourceB");
        JtaTransactionManager jtaTransactionManager = (JtaTransactionManager) ctx.getBean("transactionManager");
        try {
            jtaTransactionManager.getTransactionManager().begin();
            Connection connectionA = dataSourceA.getConnection();
            Connection connectionB = dataSourceB.getConnection();
            PreparedStatement pstmA = connectionA.prepareStatement("INSERT INTO  jta_temp (NAME ) VALUES (?)");
            pstmA.setString(1, "dataA");
            pstmA.execute();

            PreparedStatement pstmB = connectionB.prepareStatement("INSERT INTO  jta_temp (NAME ) VALUES (?)");
            pstmB.setString(1, "dataB");
            pstmB.execute();

            jtaTransactionManager.getTransactionManager().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //rabbit demo
        RabbitProducer rabbitProducer = (RabbitProducer) ctx.getBean("rabbitProducer");
        rabbitProducer.send("test-rabbit");

    }
}
