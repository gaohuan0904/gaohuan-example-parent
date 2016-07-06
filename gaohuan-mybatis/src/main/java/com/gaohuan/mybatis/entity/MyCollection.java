
package com.gaohuan.mybatis.entity;


/**
 * 我的个人收藏实体
 *
 * @author MingDingLi
 * @version 2013-12-05
 */
public class MyCollection extends BaseEntity {

    /**
     * 个人收藏实体
     */
    private Integer zid;//自增id
    private String uid;
    private String userCode;//用户编码
    private String farmCode;//农庄编码
    private String createTime;//创建时间
    private String updateTime;//更新时间

    public Integer getZid() {
        return zid;
    }

    public void setZid(Integer zid) {
        this.zid = zid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getFarmCode() {
        return farmCode;
    }

    public void setFarmCode(String farmCode) {
        this.farmCode = farmCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}