package com.xq.Railway.model;


import java.io.Serializable;

/**
 * 操作日志实体类
 */
public class Syslog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1536589822413032209L;
	
	private String optId;
	private String loginId;
	private String loginName;
	private String ipAddress;
	private String methodName;
	private String methodRemark;
	private String operatingcontent;
	private String optDate;
	// 模糊条件
	private String serchCondition;
	
	public Syslog() {
		// TODO Auto-generated constructor stub
	}

	public Syslog(String optId, String loginId, String loginName, String ipAddress, String methodName,
			String methodRemark, String operatingcontent, String optDate, String serchCondition) {
		super();
		this.optId = optId;
		this.loginId = loginId;
		this.loginName = loginName;
		this.ipAddress = ipAddress;
		this.methodName = methodName;
		this.methodRemark = methodRemark;
		this.operatingcontent = operatingcontent;
		this.optDate = optDate;
		this.serchCondition = serchCondition;
	}

	

	public String getOptId() {
		return optId;
	}

	public void setOptId(String optId) {
		this.optId = optId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodRemark() {
		return methodRemark;
	}

	public void setMethodRemark(String methodRemark) {
		this.methodRemark = methodRemark;
	}

	public String getOperatingcontent() {
		return operatingcontent;
	}

	public void setOperatingcontent(String operatingcontent) {
		this.operatingcontent = operatingcontent;
	}

	public String getOptDate() {
		return optDate;
	}

	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	public String getSerchCondition() {
		return serchCondition;
	}

	public void setSerchCondition(String serchCondition) {
		this.serchCondition = serchCondition;
	}

	@Override
	public String toString() {
		return "Syslog [optId=" + optId + ", loginId=" + loginId + ", loginName=" + loginName + ", ipAddress="
				+ ipAddress + ", methodName=" + methodName + ", methodRemark=" + methodRemark + ", operatingcontent="
				+ operatingcontent + ", optDate=" + optDate + ", serchCondition=" + serchCondition + "]";
	}

}