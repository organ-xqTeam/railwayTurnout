package com.xq.Railway.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xq.Railway.logAop.MethodLog;
import com.xq.Railway.model.administrator;
import com.xq.Railway.service.impl.AdministratorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;



/**
 * 管理员
 * @ClassName AdministratorController
 * @Author 
 * @Date 2018年9月11日 下午12:15:41
 */
@Api(tags="管理员")
@RestController
@RequestMapping("/administrator")
public class AdministratorController {
	
	@Autowired
	private AdministratorService ias;
	
	@Autowired
	private HttpServletRequest request;
	
	
	/**
	 * 新增管理员
	 * yangweihang
	 * @Date 2018年9月11日 下午12:07:16
	 * @param a	管理员类
	 * @return
	 * http://localhost:8089/administrator/insertadmin?aname=admin&ausername=admin&apwd=admin&aphonenum=123&aemile123@qq.com&arole=1
	 */
	@ApiOperation(value="新增管理员", notes="根据administrator对象创建")
	@ApiImplicitParam(name = "a", value = "详细实体administrator", required = true, dataType = "administrator")
	@RequestMapping(value = "/insertadmin" ,method = RequestMethod.POST)
	@MethodLog(remark = "新增管理员")
	public JSONObject insertadmin(@RequestBody administrator a) {
		JSONObject result = ias.insertadmin(a);
		return result;
	}
	/**
	 * 查询管理员
	 * @param a
	 * @return
	 */
	@RequestMapping(value = "/selectAlladmin", method = RequestMethod.GET)
	@MethodLog(remark = "查询所有管理员")
	public JSONObject selectAlladmin(Integer  pageNum,Integer  pageSize) {
		JSONObject object = ias.selectAllgid(pageNum,pageSize);
		return object;
	}

	
	
	/**
	 * 后台管理员登陆
	 * yangweihang
	 * @Date 2018年9月11日 下午1:32:11
	 * @param ausername	用户名
	 * @param apwd	密码
	 * @return
	 * http://localhost:8089/administrator/adminlogin?ausername=admin&apwd=admin
	 */
	@RequestMapping(value="/adminlogin" , method = RequestMethod.GET)
	@MethodLog(remark = "登陆")
	public JSONObject adminlogin(String ausername,String apwd) {
		JSONObject a = ias.selectadmin(ausername,apwd);
		request.getSession().setAttribute("login", a.get("obj"));
		return a;
	}
	
	
	/**
	 * 
	 * 安卓端登陆
	 * @param ausername
	 * @param apwd
	 * @return
	 */
	
	@RequestMapping(value= "/login" , method = RequestMethod.GET)
	@MethodLog(remark = "安卓端登陆")
	public JSONObject login(String ausername,String apwd) {
		JSONObject a = ias.adminlogin(ausername,apwd);
		return a;
	}
	
	
	
	
	/**
	 * 退出
	 * yangweihang
	 * @Date 2018年9月6日 下午7:34:07
	 * @return
	 */
	@RequestMapping(value = "/dropout" , method = RequestMethod.GET)
	@MethodLog(remark = "退出登陆")
	public String dropout() {
		request.getSession().invalidate();
		return "exit.login";
	}
	
	
	@RequestMapping(value ="/delete", method = RequestMethod.GET)
	@MethodLog(remark = "删除管理员")
	public JSONObject delete(String id) {
		JSONObject jsonObject = new JSONObject();
		int a = ias.DeleteAdmin(id);
		
		if (a > 0) {
			jsonObject.put("stats", "success");
			jsonObject.put("code", "200");
			jsonObject.put("message", "");
		}else {
			jsonObject.put("stats", "fail");
			jsonObject.put("code", "200");
			jsonObject.put("message", "");
		}
		
		return jsonObject;
	}
	
	
	/**
	 * 按aid查询
	 * yangweihang
	 * @Date 2018年9月20日 上午11:13:10
	 * @return 
	 */
	@RequestMapping(value = "/selectByAid", method = RequestMethod.GET)
	public administrator selectByAid(String aid){
		administrator sela = ias.selectByAid(aid);
		return sela;
	}
	
	/**
	 * 修改信息
	 * 
	 * yangweihang
	 * @Date 2018年10月16日 下午9:29:41
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@MethodLog(remark = "更新管理员")
	public int updateadmin(@RequestBody administrator a) {
		int n =  ias.updateAdmin(a);
		return n;
	}
}
