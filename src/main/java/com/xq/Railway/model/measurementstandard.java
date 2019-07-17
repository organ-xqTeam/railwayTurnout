package com.xq.Railway.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 
 * 测量标准
 * @author XingPanST
 *
 */
@ApiModel(description="测量标准-项点标准")
public class measurementstandard {
	@ApiModelProperty(hidden=true)
    private String id;
	
	
	@ApiModelProperty(value="测量项id" ,name = "dcbh",example="1", position = 0)
	private String dcbh;//测量项
	
    @ApiModelProperty(value="测量项名称" ,name = "measurementitem",example="轨距", position = 0)
    private String measurementitem;//测量项
    @ApiModelProperty(value="是否为系统判断项点 0：手动判断 1：机器判断 " ,name = "state",example="0/1", position = 6)
    private String state;//范围
    @ApiModelProperty(value="id:为轨距关联详细标准" ,name = "gaugestandardid",example="id", position = 6)
    private String gaugestandardid;//范围
    @ApiModelProperty(value="if state:1 ranges:第一个数字 " ,name = "range1",example="1", position = 7)
    private String ranges;//范围
    @ApiModelProperty(value="if state:1 如果有两个数字 ranges:第二个数字" ,name = "range1", example="2",position = 7)
    private String standard;
    @ApiModelProperty(value="if state:0 range1:文字描述  state:1 range1：符号算法id " ,name = "range1", position = 7)
    private String range1;//范围 //轨距和 系统判断的都要选 计算符号
    @ApiModelProperty(value="检查意见" ,name = "standard1",example="检查意见", position = 5)
    private String standard1;//测量标准
    @ApiModelProperty(value="道岔标准id" ,name = "turnoutstandardid",example="道岔标准id", position = 1)
    private String turnoutstandardid;//道岔标准id
    @ApiModelProperty(value="检查方法和数量" ,name = "inspectionmethod",example="检查方法和数量", position = 2)
    private String inspectionmethod;//检查方法和数量
    @ApiModelProperty(value="管理类别A/B/C" ,name = "managementcategory",example="A/B/C", position = 3)
    private String managementcategory;//管理类别
    @ApiModelProperty(value="是否有详细标准 0：没有 1：有" ,name = "isdetails",example="0/1", position = 4)
    private String isdetails;//管理类别
    @ApiModelProperty(hidden=true)
    private String isdelete;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
	public String getDcbh() {
		return dcbh;
	}
	public void setDcbh(String dcbh) {
		this.dcbh = dcbh;
	}
	public String getMeasurementitem() {
		return measurementitem;
	}
	public void setMeasurementitem(String measurementitem) {
		this.measurementitem = measurementitem;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getGaugestandardid() {
		return gaugestandardid;
	}
	public void setGaugestandardid(String gaugestandardid) {
		this.gaugestandardid = gaugestandardid;
	}
	public String getRanges() {
		return ranges;
	}
	public void setRanges(String ranges) {
		this.ranges = ranges;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getRange1() {
		return range1;
	}
	public void setRange1(String range1) {
		this.range1 = range1;
	}
	public String getStandard1() {
		return standard1;
	}
	public void setStandard1(String standard1) {
		this.standard1 = standard1;
	}
	public String getTurnoutstandardid() {
		return turnoutstandardid;
	}
	public void setTurnoutstandardid(String turnoutstandardid) {
		this.turnoutstandardid = turnoutstandardid;
	}
	public String getInspectionmethod() {
		return inspectionmethod;
	}
	public void setInspectionmethod(String inspectionmethod) {
		this.inspectionmethod = inspectionmethod;
	}
	public String getManagementcategory() {
		return managementcategory;
	}
	public void setManagementcategory(String managementcategory) {
		this.managementcategory = managementcategory;
	}
	public String getIsdetails() {
		return isdetails;
	}
	public void setIsdetails(String isdetails) {
		this.isdetails = isdetails;
	}
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	@Override
	public String toString() {
		return "measurementstandard [id=" + id + ", dcbh=" + dcbh + ", measurementitem=" + measurementitem + ", state="
				+ state + ", gaugestandardid=" + gaugestandardid + ", ranges=" + ranges + ", standard=" + standard
				+ ", range1=" + range1 + ", standard1=" + standard1 + ", turnoutstandardid=" + turnoutstandardid
				+ ", inspectionmethod=" + inspectionmethod + ", managementcategory=" + managementcategory
				+ ", isdetails=" + isdetails + ", isdelete=" + isdelete + "]";
	}

    
    
    
}