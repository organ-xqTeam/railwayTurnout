package com.xq.Railway.model;

import io.swagger.annotations.ApiModelProperty;

/*
 * 
 * 
 * 
 * 火车线路信息
 */
public class trainroute {
	
	@ApiModelProperty(hidden=true)
    private String id; 

    private String routename; //线路名称
    @ApiModelProperty(hidden=true)
    private String isdelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trainroute.routename
     *
     * @return the value of trainroute.routename
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public String getRoutename() {
        return routename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trainroute.routename
     *
     * @param routename the value for trainroute.routename
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public void setRoutename(String routename) {
        this.routename = routename == null ? null : routename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trainroute.isdelete
     *
     * @return the value of trainroute.isdelete
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public String getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trainroute.isdelete
     *
     * @param isdelete the value for trainroute.isdelete
     *
     * @mbggenerated Mon Feb 11 12:09:53 CST 2019
     */
    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }

	@Override
	public String toString() {
		return "trainroute [id=" + id + ", routename=" + routename + ", isdelete=" + isdelete + "]";
	}
    
}