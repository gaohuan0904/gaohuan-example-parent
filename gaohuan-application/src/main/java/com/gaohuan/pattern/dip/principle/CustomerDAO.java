package com.gaohuan.pattern.dip.principle;

/**
 * Created by acer on 2016/7/6.
 */
public class CustomerDAO {
    private DataConvertor dataConvertor;

    public CustomerDAO(DataConvertor dataConvertor) {
        this.dataConvertor = dataConvertor;
    }


    public void addCustomer() {
        dataConvertor.readFile();
        //save Something
    }
}
