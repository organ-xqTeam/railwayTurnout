package com.xq.Railway.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 测量结果
 * @author XingPanST
 *
 */

@ApiModel(description="测量结果")
public class detectionresult {
	@ApiModelProperty(hidden=true)
    private String id;
    
    @ApiModelProperty(value="关联项目id" ,name = "mid",example="id", position = 0)
    private String mid;//关联项目id
    
    @ApiModelProperty(value="项点id" ,name = "pname",example="id", position = 1)
    private String pname;//项目名

    @ApiModelProperty(value="标准" ,name = "standards",example="标准", position = 2)
    private String standards;//标准
    
    @ApiModelProperty(value="标准id" ,name = "standardid",example="id", position = 3)
    private String standardid;//标准

    @ApiModelProperty(value="实测数据" ,name = "measureddata",example="2", position = 4)
    private String measureddata;//实测数据
    /**
     * 
     * 轨距中 里程
     */
    @ApiModelProperty(value="实测数据1" ,name = "measureddata1",example="2", position = 5)
    private String measureddata1;//实测数据

    @ApiModelProperty(value="结果" ,name = "measuredresults",example="不合格/合格", position = 6)
    private String measuredresults;//结果
    @ApiModelProperty(hidden=true)
    private String isdelete;
    
    
    @ApiModelProperty(value="图片id" ,name = "measuredresults",example="", position = 6)
    private String imgs;//结果

    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column detectionresult.id
     *
     * @param id the value for detectionresult.id
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMeasureddata1() {
		return measureddata1;
	}

	public void setMeasureddata1(String measureddata1) {
		this.measureddata1 = measureddata1;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column detectionresult.mid
     *
     * @return the value of detectionresult.mid
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public String getMid() {
        return mid;
    }
    
    public String getStandards() {
		return standards;
	}

	public void setStandards(String standards) {
		this.standards = standards;
	}

	public String getStandardid() {
		return standardid;
	}

	public void setStandardid(String standardid) {
		this.standardid = standardid;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column detectionresult.mid
     *
     * @param mid the value for detectionresult.mid
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public void setMid(String mid) {
        this.mid = mid == null ? null : mid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column detectionresult.pname
     *
     * @return the value of detectionresult.pname
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public String getPname() {
        return pname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column detectionresult.pname
     *
     * @param pname the value for detectionresult.pname
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column detectionresult.standard
     *
     * @return the value of detectionresult.standard
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column detectionresult.standard
     *
     * @param standard the value for detectionresult.standard
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column detectionresult.measureddata
     *
     * @return the value of detectionresult.measureddata
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public String getMeasureddata() {
        return measureddata;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column detectionresult.measureddata
     *
     * @param measureddata the value for detectionresult.measureddata
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public void setMeasureddata(String measureddata) {
        this.measureddata = measureddata == null ? null : measureddata.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column detectionresult.measuredresults
     *
     * @return the value of detectionresult.measuredresults
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public String getMeasuredresults() {
        return measuredresults;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column detectionresult.measuredresults
     *
     * @param measuredresults the value for detectionresult.measuredresults
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public void setMeasuredresults(String measuredresults) {
        this.measuredresults = measuredresults == null ? null : measuredresults.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column detectionresult.isdelete
     *
     * @return the value of detectionresult.isdelete
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public String getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column detectionresult.isdelete
     *
     * @param isdelete the value for detectionresult.isdelete
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }

	@Override
	public String toString() {
		return "detectionresult [id=" + id + ", mid=" + mid + ", pname=" + pname + ", standards=" + standards
				+ ", standardid=" + standardid + ", measureddata=" + measureddata + ", measureddata1=" + measureddata1
				+ ", measuredresults=" + measuredresults + ", isdelete=" + isdelete + "]";
	}
    
}