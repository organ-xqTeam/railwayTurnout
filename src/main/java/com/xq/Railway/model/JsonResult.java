package com.xq.Railway.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 返回实体类
 * @author XingPanST
 *
 */
@ApiModel(description = "通用响应返回对象")
public class JsonResult {

	@ApiModelProperty(value = "状态",example="ok/error", position = 1)
	private String status = null;
	@ApiModelProperty(value = "返回结果", position = 2)
	private Object result = null;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	
}