package com.xq.Railway.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 
 * 道岔分类
 * @author XingPanST
 *
 */
public class turnouttype implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4620758487514863801L;
	@ApiModelProperty(hidden=true)
    private String id;
    
    private String turnoutTypename;//道岔分类名称

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
		return turnoutTypename;
	}

	public void setTurnouttypename(String turnouttypename) {
		this.turnoutTypename = turnouttypename;
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