package com.xq.Railway.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 计算标准
 * @author XingPanST
 *
 */
@ApiModel(description = "计算标准")
public class calculationstandard {
	
	
    private String id;

	@ApiModelProperty(value = "描述",example="大于等于", position = 0)
    private String calculation;//道岔标准id
	@ApiModelProperty(value = "符号",example="+", position = 1)
    private String symbol1;//里程
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCalculation() {
		return calculation;
	}
	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}
	public String getSymbol1() {
		return symbol1;
	}
	public void setSymbol1(String symbol1) {
		this.symbol1 = symbol1;
	}
	
	
    
}