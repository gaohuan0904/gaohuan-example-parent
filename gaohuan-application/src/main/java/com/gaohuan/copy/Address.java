package com.gaohuan.copy;

/**
 * Created by gh on 2016/3/2 0002.
 */
public class Address {
    private String region;

    private String fullAddress;

    public Address(String region, String fullAddress) {
        this.region = region;
        this.fullAddress = fullAddress;
    }

    public Address() {
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    @Override
    public String toString() {
        return "Address{" +
                "region='" + region + '\'' +
                ", fullAddress='" + fullAddress + '\'' +
                '}';
    }
}


