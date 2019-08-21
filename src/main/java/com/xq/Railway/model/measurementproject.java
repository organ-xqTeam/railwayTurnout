package com.xq.Railway.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 
 * 测量项目
 * @author XingPanST
 *
 */
@ApiModel(description="测量项目")
public class measurementproject {
	@ApiModelProperty(hidden=true)
    private int id;
	@ApiModelProperty(value = "线路ID",example="id", position = 0)
    private String tid;//线路id
	@ApiModelProperty(value = "站点id",example="id", position = 1)
    private String lid;//站点id
	@ApiModelProperty(value = "管理员id",example="id", position = 2)
    private String aid;//管理员id
	@ApiModelProperty(value = "测量标准id",example="id", position = 3)
    private String turnoutstandardid;//测量标准id

	@ApiModelProperty(value = "项目名",example="项目名", position = 4)
    private String pname;//项目名
	@ApiModelProperty(value = "测量时间",example="2019-05-31", position = 5)
    private String ptime;//测量时间
	@ApiModelProperty(value = "上传文件名",example="", position = 7)
	private String file;//测量时间
	@ApiModelProperty(value = "关联项点id",example="id", position = 6)
    private String warningstatistics;//预警统计

    private String datasources;//数据来源
    private String distance;//轨距
    private String lAltitude;//左高程
    private String rAltitude;//右高程
    @ApiModelProperty(hidden=true)
    private String isdelete;


    
    
    
    public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getTurnoutstandardid() {
		return turnoutstandardid;
	}

	public void setTurnoutstandardid(String turnoutstandardid) {
		this.turnoutstandardid = turnoutstandardid;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getlAltitude() {
		return lAltitude;
	}

	public void setlAltitude(String lAltitude) {
		this.lAltitude = lAltitude;
	}

	public String getrAltitude() {
		return rAltitude;
	}

	public void setrAltitude(String rAltitude) {
		this.rAltitude = rAltitude;
	}

	public String getTid() {
        return tid;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid == null ? null : lid.trim();
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime == null ? null : ptime.trim();
    }

    public String getWarningstatistics() {
        return warningstatistics;
    }

    public void setWarningstatistics(String warningstatistics) {
        this.warningstatistics = warningstatistics == null ? null : warningstatistics.trim();
    }

    public String getDatasources() {
        return datasources;
    }

    public void setDatasources(String datasources) {
        this.datasources = datasources == null ? null : datasources.trim();
    }

    
    
    
    public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }

	@Override
	public String toString() {
		return "measurementproject [id=" + id + ", tid=" + tid + ", lid=" + lid + ", aid=" + aid
				+ ", turnoutstandardid=" + turnoutstandardid + ", pname=" + pname + ", ptime=" + ptime
				+ ", warningstatistics=" + warningstatistics + ", datasources=" + datasources + ", distance=" + distance
				+ ", lAltitude=" + lAltitude + ", rAltitude=" + rAltitude + ", isdelete=" + isdelete + "]";
	}
    
}