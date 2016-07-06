
package com.gaohuan.mybatis.entity;


/**
 * 农庄实体
 *
 * @author MingDingLi
 * @version 2013-12-05
 */
public class Farm extends BaseEntity {

    /**
     * 农庄实体
     */
    private Integer zid;//自增id
    private String uid;
    private String name;//农庄名称
    private String farmcode;//农庄code
    private String createTime;//创建日期
    private String updateTime;//更新日期
    private String startTime;//有效开始日期
    private String endTime;//有效结束日期
    private String style;//样式风格
    private String pic;//图片
    private String mainPic;//封面图片
    private Double price;//价格
    private String unit;//单位
    private String categoryCode;//所属分类code
    private String level;//星级
    private String longitude;//经度
    private String latitude;//纬度
    private String year;//年限
    private String address;//地址
    private String userCode;//所属用户code
    private String status;//上下架状态
    private String farmDescription;//农庄描述
    private Integer clicks;//点击量
    private String customStatus;//定制类型
    private String creator;//创建人
    private String allowPayType;//允许支付类型
    private String auditStatus;//审核状态
    /**
     * 农庄配套设施(用于上传农庄配套设施)
     */
    private String deviceCode;

    public Farm() {
        super();
    }

    public Farm(String id) {
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAllowPayType() {
        return allowPayType;
    }

    public void setAllowPayType(String allowPayType) {
        this.allowPayType = allowPayType;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public Integer getZid() {
        return zid;
    }

    public void setZid(Integer zid) {
        this.zid = zid;
    }


    public String getFarmcode() {
        return farmcode;
    }

    public void setFarmcode(String farmcode) {
        this.farmcode = farmcode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    //风格编码
    public static final String TYPE_STYLE_FRENCH = "449700060001";
    public static final String TYPE_STYLE_BRITISH = "449700060002";

    public String getStyle() {
        if (style != null) {
            switch (style) {
                case TYPE_STYLE_FRENCH:
                    style = "法式";
                    break;
                case TYPE_STYLE_BRITISH:
                    unit = "英式";
                    break;
            }
        }
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

/*	//上下架状态编码
	public static final String TYPE_STATUS_UP = "449700030001";
	public static final String TYPE_STATUS_DOWN = "449700030002";
	public String getStatus() {
		if(status!=null){
			switch(status) {
				case TYPE_STATUS_UP:
					status = "上架";
					break;
				case TYPE_STATUS_DOWN:
					status = "下架";
					break;
			}
		}
		return status;
	}*/

    public String getCustomStatus() {
        return customStatus;
    }

    public void setCustomStatus(String customStatus) {
        this.customStatus = customStatus;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getFarmDescription() {
        return farmDescription;
    }

    public void setFarmDescription(String farmDescription) {
        this.farmDescription = farmDescription;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

}