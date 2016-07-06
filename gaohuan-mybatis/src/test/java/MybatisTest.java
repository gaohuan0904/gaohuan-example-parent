import com.gaohuan.mybatis.dao.CollectionDao;
import com.gaohuan.mybatis.entity.MyCollection;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by acer on 2016/6/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {

    @Autowired
    private CollectionDao collectionDao;

    @Test
    public void test() {
        collectionDao.selectOne("1");
    }
}
