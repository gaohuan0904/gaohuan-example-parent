package com.gaohuan.generator;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * Created by gh on 2015/12/2.
 */
public class DBUtil {
    private static BasicDataSource basicDataSource;

    static {
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://192.168.0.8:3308/redbag");
        basicDataSource.setUsername("redbag");
        basicDataSource.setPassword("redbag123");
    }

    public static DataSource getDataSource() {
        return basicDataSource;
    }

    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }

    /**
     * @param
     * @param includeTable
     * @throws Exception
     */
    public static List<Map> makeModelMap(List<String> includeTable) throws Exception {


        List<Map> resultModel = new ArrayList<Map>();

        DatabaseMetaData dbmd = basicDataSource.getConnection().getMetaData();
        ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});
        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");
            if (includeTable.contains(tableName)) {
                ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");

                Map model = new HashMap();

                int index = tableName.indexOf("_");
                String camelNameObj = CamelCaseUtils.toCamelCase(tableName.substring(index + 1));
                String className = CamelCaseUtils.toCamelCaseClass(camelNameObj);
                String basePackage = CodeGenerator.getStringByKey(CodeGenerator.BASEPACKAGE_KEY_NAME);
                String mapperPackage = CodeGenerator.getStringByKey(CodeGenerator.MAPPER_PATH_KEY_NAME);
                String classType = basePackage + "." + className;
                String  tableRemarks= getTableComment(tableName,basicDataSource.getConnection());

                model.put("tableRemarks",tableRemarks);
                model.put("mapperPackage", mapperPackage);
                model.put("table", tableName);//表名
                model.put("classType", classType);//带包的类名
                model.put("resultMap", camelNameObj + "Map");
                model.put("sqlColumn", camelNameObj + "_column");
                model.put("mapperNamespace", mapperPackage +"." +className + "Mapper");// map文件命名空间
                model.put("className", className);//类名
                model.put("createTime", Calendar.getInstance().getTime());

                List list = new ArrayList<>();
                while (rs.next()) {
                    Map map = new HashMap();
                    map.put("columnName", rs.getString("COLUMN_NAME"));//列明
                    map.put("columnType", rs.getString("TYPE_NAME"));//类型
                    map.put("objectName", CamelCaseUtils.toCamelCase(rs.getString("COLUMN_NAME")));//java属性
                    map.put("upobjectName", CamelCaseUtils.toCamelCaseClass(CamelCaseUtils.toCamelCase(rs.getString("COLUMN_NAME")))) ;//java属性
                    map.put("columnRemark", rs.getString("REMARKS"));
                    list.add(map);
                }
                model.put("columns", list);
                resultModel.add(model);
            }
        }
        return resultModel;
    }

    private static String getTableComment(String tableName, Connection conn) throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        String result = "";
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='" + tableName + "'");
            while (rs.next()) {
                result = rs.getString(1);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }
        return result;
    }

}
