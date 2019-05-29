package com.xq.Railway.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 道岔标准
 * 
 * @author XingPanST
 *
 */
public class turnoutstandard {
	@ApiModelProperty(hidden=true)
    private String id;//

    private String turnoutid;//道岔分类

    private String turnoutstandard;//名称

    private String isdelete;

    private String qualityid;//关联质量分析标准

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

    public String getTurnoutstandard() {
        return turnoutstandard;
    }

    public void setTurnoutstandard(String turnoutstandard) {
        this.turnoutstandard = turnoutstandard == null ? null : turnoutstandard.trim();
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }

    public String getQualityid() {
        return qualityid;
    }

    public void setQualityid(String qualityid) {
        this.qualityid = qualityid == null ? null : qualityid.trim();
    }
}