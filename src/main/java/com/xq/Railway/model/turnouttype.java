package com.xq.Railway.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 
 * 道岔分类
 * @author XingPanST
 *
 */
@ApiModel(description="道岔分类对象")
public class turnouttype {
	
	/**
	 * 
	 */
	@ApiModelProperty(hidden=true)
    private String id;
    
	@ApiModelProperty(value="道岔分类名称" ,name = "turnouttypename",example="18号", position = 0)
    private String turnouttypename;//道岔分类名称
	
	
	@ApiModelProperty(value = "备注", position = 1)
    private String reamke;
	
	
    @ApiModelProperty(hidden=true)
    private String isdelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTurnouttypename() {
		return turnouttypename;
	}

	public void setTurnouttypename(String turnouttypename) {
		this.turnouttypename = turnouttypename;
	}

	public String getReamke() {
        return reamke;
    }

    public void setReamke(String reamke) {
        this.reamke = reamke == null ? null : reamke.trim();
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }
}