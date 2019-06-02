package com.xq.Railway.model;

import io.swagger.annotations.ApiModel;

/**
 * 
 * 
 * 
 * 首页信息
 * @author XingPanST
 *
 */
@ApiModel(description="首页信息")
public class mainpageinformaction {
    private String id;

    private String rid; //关联站点id

    private String weather; //天气id
    private String weatherid; //天气

    private String geographical; //地理位置

    private String projectoverview;//项目概况
    private String progressconstruction;//施工进展

    private String noticeowner;//业主通知

    private String isdelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWeatherid() {
		return weatherid;
	}

	public void setWeatherid(String weatherid) {
		this.weatherid = weatherid;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mainpageinformaction.rid
     *
     * @return the value of mainpageinformaction.rid
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public String getRid() {
        return rid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mainpageinformaction.rid
     *
     * @param rid the value for mainpageinformaction.rid
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mainpageinformaction.weather
     *
     * @return the value of mainpageinformaction.weather
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public String getWeather() {
        return weather;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mainpageinformaction.weather
     *
     * @param weather the value for mainpageinformaction.weather
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public void setWeather(String weather) {
        this.weather = weather == null ? null : weather.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mainpageinformaction.geographical
     *
     * @return the value of mainpageinformaction.geographical
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public String getGeographical() {
        return geographical;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mainpageinformaction.geographical
     *
     * @param geographical the value for mainpageinformaction.geographical
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public void setGeographical(String geographical) {
        this.geographical = geographical == null ? null : geographical.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mainpageinformaction.projectoverview
     *
     * @return the value of mainpageinformaction.projectoverview
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public String getProjectoverview() {
        return projectoverview;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mainpageinformaction.projectoverview
     *
     * @param projectoverview the value for mainpageinformaction.projectoverview
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public void setProjectoverview(String projectoverview) {
        this.projectoverview = projectoverview == null ? null : projectoverview.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mainpageinformaction.progressconstruction
     *
     * @return the value of mainpageinformaction.progressconstruction
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public String getProgressconstruction() {
        return progressconstruction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mainpageinformaction.progressconstruction
     *
     * @param progressconstruction the value for mainpageinformaction.progressconstruction
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public void setProgressconstruction(String progressconstruction) {
        this.progressconstruction = progressconstruction == null ? null : progressconstruction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mainpageinformaction.noticeowner
     *
     * @return the value of mainpageinformaction.noticeowner
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public String getNoticeowner() {
        return noticeowner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mainpageinformaction.noticeowner
     *
     * @param noticeowner the value for mainpageinformaction.noticeowner
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public void setNoticeowner(String noticeowner) {
        this.noticeowner = noticeowner == null ? null : noticeowner.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mainpageinformaction.isdelete
     *
     * @return the value of mainpageinformaction.isdelete
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public String getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mainpageinformaction.isdelete
     *
     * @param isdelete the value for mainpageinformaction.isdelete
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }
}