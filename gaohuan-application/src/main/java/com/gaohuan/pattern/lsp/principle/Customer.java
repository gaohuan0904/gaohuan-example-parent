package com.gaohuan.pattern.lsp.principle;

/**
 * Created by acer on 2016/7/6.
 */
public abstract class Customer {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
