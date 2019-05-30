package com.xq.Railway.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 轨距测量标准
 * @author XingPanST
 *
 */
public class gaugestandard {
	@ApiModelProperty(hidden=true)
    private String id;

    private String turnoutid;//道岔标准id

    private String mileage;//里程

    private String gauge;//轨距
    @ApiModelProperty(hidden=true)
    private String isdelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTurnoutid() {
        return turnoutid;
    }

    public void setTurnoutid(String turnoutid) {
        this.turnoutid = turnoutid == null ? null : turnoutid.trim();
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage == null ? null : mileage.trim();
    }

    public String getGauge() {
        return gauge;
    }

    public void setGauge(String gauge) {
        this.gauge = gauge == null ? null : gauge.trim();
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }
}