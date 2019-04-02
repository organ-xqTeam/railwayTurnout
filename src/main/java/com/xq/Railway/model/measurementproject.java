package com.xq.Railway.model;
/**
 * 
 * 
 * 测量项目
 * @author XingPanST
 *
 */
public class measurementproject {
    private int id;

    private String tid;//线路id

    private String lid;//站点id
    
    private String aid;//管理员id

    private String pname;//项目名

    private String ptime;//测量时间

    private String warningstatistics;//预警统计

    private String datasources;//数据来源
    private String distance;//轨距
    private String lAltitude;//左高程
    private String rAltitude;//右高程

    private String isdelete;


    
    
    
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
}