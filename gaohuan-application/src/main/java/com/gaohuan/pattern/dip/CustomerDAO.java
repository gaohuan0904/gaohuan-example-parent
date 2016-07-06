package com.gaohuan.pattern.dip;

/**
 * Created by acer on 2016/7/6.
 */
public class CustomerDAO {

    public void addCustomers(TxtDataConvertor txtDataConvertor) {
        txtDataConvertor.readFile();
    }

    public void addCustomers(ExcelDataConvertor excelDataConvertor) {
        excelDataConvertor.readFile();
    }
}
