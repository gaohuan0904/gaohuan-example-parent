
package com.gaohuan.mybatis.entity;


/**
 * 公共属性实体
 *
 * @author MingDingLi
 * @version 2013-12-05
 */
public class BaseEntity {
    private String sid;//用户会话id
    private String version;//版本号
    private String updateTime;
    private String createTime;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}