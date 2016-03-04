package com.gaohuan.biz.business;

import com.gaohuan.biz.common.BusinessResult;
import com.gaohuan.biz.entity.User;

/**
 * Created by gh on 2016/3/4 0004.
 */
public interface UserBusiness {

    /**
     * 注册用户
     *
     * @param user
     */
    public BusinessResult<String> signIn(User user);
}
