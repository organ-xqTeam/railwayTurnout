package com.xq.Railway.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 质量分析标准
 * @author XingPanST
 *
 */
public class qualitystandard {
	
	@ApiModelProperty(hidden=true)
    private String id;
	
	
	private String identifiernum;//分类
	
    private String msg; //描述信息

    private String num;//数字

    private String msg1;//描述信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getIdentifiernum() {
		return identifiernum;
	}

	public void setIdentifiernum(String identifiernum) {
		this.identifiernum = identifiernum;
	}

	public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1 == null ? null : msg1.trim();
    }

	@Override
	public String toString() {
		return "qualitystandard [id=" + id + ", identifiernum=" + identifiernum + ", msg=" + msg + ", num=" + num
				+ ", msg1=" + msg1 + "]";
	}
    
}