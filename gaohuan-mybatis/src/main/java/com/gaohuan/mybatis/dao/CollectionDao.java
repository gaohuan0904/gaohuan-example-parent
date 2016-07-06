
package com.gaohuan.mybatis.dao;


import com.gaohuan.mybatis.annotation.MyBatisDao;
import com.gaohuan.mybatis.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author caohuajin
 * @Description: 收藏 Dao
 * @date 2016年2月26日 上午11:41:55
 */
@MyBatisDao
public interface CollectionDao extends CrudDao<FarmList> {
    /**
     * 用户收藏列表查询
     *
     * @param user
     * @return
     */
    public List<FarmList> query(@Param("user") User user, @Param("page") Page<Map<String, Object>> page);

    public int delete(@Param("user") User user, @Param("farm") Farm farm);

    public int add(MyCollection myCollection);

    public List<MyCollection> queryCollection(@Param("userCode") String userCode, @Param("farmCode") String farmCode);
}
