package com.xq.Railway.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 轨距测量标准
 * @author XingPanST
 *
 */
@ApiModel(description = "轨距测量标准")
public class gaugestandard {
	@ApiModelProperty(hidden=true)
    private String id;

	@ApiModelProperty(value = "关联项点id",example="id", position = 0)
    private String turnoutid;//道岔标准id
	@ApiModelProperty(value = "里程",example="1734", position = 1)
    private String mileage;//里程
	
	@ApiModelProperty(value = "轨距",example="1", position = 2)
    private String gauge;//轨距
	@ApiModelProperty(value = "轨枕编号",example="1", position = 2)
	private String rownum;//轨距
	
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


	public String getRownum() {
		return rownum;
	}


	public void setRownum(String rownum) {
		this.rownum = rownum;
	}


	@Override
	public String toString() {
		return "gaugestandard [id=" + id + ", turnoutid=" + turnoutid + ", mileage=" + mileage + ", gauge=" + gauge
				+ ", isdelete=" + isdelete + "]";
	}
    
}