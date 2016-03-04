package com.gaohuan.copy;

/**
 * Created by gh on 2016/3/2 0002.
 */
public class User {

    private Address address;

    private String name;

    private Integer age;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "address=" + address +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
