package com.gaohuan.mybatis.entity;


import com.gaohuan.mybatis.SystemEnum;

/**
 * 农庄列表查询结果集
 *
 * @author 化金
 */
public class FarmList {
    /**
     * 农庄信息
     */
    private String name = " ";//农庄名称
    private String farmcode = " ";//农庄code
    private String address = " ";//农庄地址
    private String pic = " ";//图片
    private Double price = 0.0;//原价格
    private String unit = " ";//单位
    private String unitName = " ";//单位名称
    private String level = " ";//星级
    private String farmDescription = " ";//农庄描述
    private String categoryCode = " ";//分类编码
    private String status = " ";//农庄上下架状态
    //  private String
    /*
	 * 活动信息
	 */
    private String currentTime = " ";//当前服务器时间
    private String startTime = " ";//活动开始时间
    private String endTime = " ";//活动结束时间
    private Double discountPrice = 0.0;//折后价
    private Double minus = 0.0;//立减
    private Double discount = 0.0;//折扣数
    /*
     * 附近农庄
     */
    private String distance = " ";//距离

    /**
     * 实体类型
     *
     * @return
     */
    private String objType = " ";//资源类型(农庄|酒店)

    public Double getMinus() {
        return minus;
    }

    public void setMinus(Double minus) {
        this.minus = minus;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFarmcode() {
        return farmcode;
    }

    public void setFarmcode(String farmcode) {
        this.farmcode = farmcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //上下架状态编码
    public static final String TYPE_STATUS_UP = "449700030001";
    public static final String TYPE_STATUS_DOWN = "449700030002";

    public String getStatus() {
        if (status != null) {
            switch (status) {
                case TYPE_STATUS_UP:
                    status = "上架";
                    break;
                case TYPE_STATUS_DOWN:
                    status = "下架";
                    break;
            }
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public String getUnitName() {
        if (unit != null) {
            switch (unit) {
                case SystemEnum.FARM_UNIT_DAY:
                    unitName = "天";
                    break;
                case SystemEnum.FARM_UNIT_MONTH:
                    unitName = "月";
                    break;
                case SystemEnum.FARM_UNIT_YEAR:
                    unitName = "年";
                    break;
            }
        }
        return unitName;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    //星级编码
    public static final String TYPE_LEVEL_0 = "449700050001";
    public static final String TYPE_LEVEL_1 = "449700050002";
    public static final String TYPE_LEVEL_2 = "449700050003";
    public static final String TYPE_LEVEL_3 = "449700050004";
    public static final String TYPE_LEVEL_4 = "449700050005";
    public static final String TYPE_LEVEL_5 = "449700050006";

    public String getLevel() {
        if (level != null) {
            switch (level) {
                case TYPE_LEVEL_0:
                    level = "0";
                    break;
                case TYPE_LEVEL_1:
                    level = "1";
                    break;
                case TYPE_LEVEL_2:
                    level = "2";
                    break;
                case TYPE_LEVEL_3:
                    level = "3";
                    break;
                case TYPE_LEVEL_4:
                    level = "4";
                    break;
                case TYPE_LEVEL_5:
                    level = "5";
                    break;
            }
        }
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFarmDescription() {
        return farmDescription;
    }

    public void setFarmDescription(String farmDescription) {
        this.farmDescription = farmDescription;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }
}
