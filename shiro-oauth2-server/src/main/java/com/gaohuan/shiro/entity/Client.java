package com.gaohuan.shiro.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * 实体bean
 */
public class Client implements Serializable {

    private Long id;//主键
    private String clientId;//客户端id
    private String clientName;//客户端名称
    private String clientSecret;//客户端安全key

    public Client() {
    }

    public Client(Long id, String clientId, String clientName, String clientSecret) {
        this.id = id;
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientSecret = clientSecret;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Client)) return false;
        Client castOther = (Client) other;
        return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }
}