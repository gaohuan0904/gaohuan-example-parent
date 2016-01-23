package ibatortest.generated.flat.java5.main;

import ibatortest.generated.flat.java5.mapper.UserMapper;
import ibatortest.generated.flat.java5.model.User;
import ibatortest.generated.flat.java5.model.UserExample;
import ibatortest.generated.flat.java5.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by huan on 2016/1/2.
 */
public class UserMain {

    public static void main(String[] args) {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        clearTable(sqlSession);

        testInsert(sqlSession);
        testCount(sqlSession);
        testUpdate(sqlSession);
        testDelete(sqlSession);

        sqlSession.close();


    }

    public static void testInsert(SqlSession sqlSession) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user1 = new User();
        user1.setId(1);
        user1.setCreateTime(new Date());
        user1.setEmail("gaohuan0904@163.com");
        user1.setUsername("gaohuan");
        user1.setPassword("password");
        user1.setUsercol("");


        User user2 = new User();
        user2.setId(2);
        user2.setCreateTime(new Date());
        user2.setEmail("gaohuan0904@163.com");
        user2.setUsername("gaohuan");
        user2.setPassword("password");
        user2.setUsercol("");

        User user3 = new User();
        user3.setId(3);
        user3.setCreateTime(new Date());
        user3.setEmail("gaohuan0904@163.com");
        user3.setUsername("gaohuan");
        user3.setPassword("password");
        user3.setUsercol("");

        userMapper.insert(user1);
        userMapper.insert(user2);
        userMapper.insert(user3);

        System.out.println("insert success");

        sqlSession.commit();
    }

    public static void testCount(SqlSession sqlSession) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUsernameEqualTo("gaohaun")
                .andIdLessThan(2)
                .andIdBetween(2, 3);
        int count = userMapper.countByExample(userExample);

        System.out.println("count= " + count);
    }

    public static void testDelete(SqlSession sqlSession) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        userMapper.deleteByPrimaryKey(1);
        sqlSession.commit();

        System.out.println("testDelete: " + 1);
    }


    public static void testUpdate(SqlSession sqlSession) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user1 = new User();
        user1.setId(1);
        user1.setCreateTime(new Date());
        user1.setEmail("gaohuan0904@163.com");
        user1.setUsername("gaohuan");
        user1.setPassword("password");
        user1.setUsercol("col");

        userMapper.updateByPrimaryKey(user1);
        sqlSession.commit();

        System.out.println("testUpdate: " + 1);
    }


    public static void clearTable(SqlSession sqlSession) {
        try {
            sqlSession.getConnection().createStatement().execute("delete from test.user");
            sqlSession.commit();

        } catch (SQLException e) {
            System.out.println("clearTable exception!");
            sqlSession.close();
        }
    }
}
