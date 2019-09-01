/**
 * 
 */
package com.jmh.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jmh.model.BaseListResult;
import com.jmh.model.BaseResult;
import com.jmh.model.Company;
import com.jmh.model.CompanyRole;
import com.jmh.model.LoginInfo;
import com.jmh.model.Role;
import com.jmh.model.User;
import com.jmh.service.AdminService;
import com.jmh.utils.Constants;
import com.jmh.utils.MsgConstants;
import com.jmh.utils.WebUtils;

/**
 * @author chen, weiwei
 * @date 08/07/2019
 *
 */
@RestController()
@RequestMapping("/rms")
public class RmsController {

	private static Log log = LogFactory.getLog(RmsController.class);

	private AdminService adminService;

	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	/**
	 * @api {post} jmh/rms/login RMS登录
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup RMS
	 * @apiName login
	 * 
	 * @apiParam {String} loginName 登录姓名
	 * @apiParam {String} loginPwd 登录密码
	 * 
	 * @apiSuccess {String} message 返回提示文本
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public BaseResult login(LoginInfo info, HttpServletRequest request, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		HttpSession session = request.getSession();
		session.setAttribute(Constants.LOGIN_INFO, info);
		boolean isAuth = adminService.isAuthorized(info);
		if (isAuth) {
			result.setStatusCode(Constants.SUCCESS);
			result.setMessage(info.getLoginName());
		} else {
			result.setStatusCode(Constants.FAILUE);
			result.setMessage(MsgConstants.RMS_NO_AUTHORIZED);
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/rms/loginOut 登出
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup RMS
	 * @apiName loginOut
	 * 
	 * @apiSuccess {String} message 返回数量
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态3登出
	 * 
	 */
	@RequestMapping(value = "/loginOut", method = RequestMethod.GET)
	public BaseResult loginOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BaseResult result = new BaseResult();
		HttpSession session = request.getSession();
		session.removeAttribute(Constants.LOGIN_INFO);
		session.invalidate();
		result.setStatusCode(Constants.LOGIN_OUT);
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/rms/getUserCount 累计注册军人数
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup RMS
	 * @apiName getUserCount
	 * 
	 * @apiSuccess {String} message 返回数量
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 * 
	 */
	@RequestMapping(value = "/getUserCount", method = RequestMethod.GET)
	public BaseResult getUserCount(HttpServletResponse response) {
		BaseResult result = new BaseResult();
		int count = adminService.getUserCount();
		result.setStatusCode(Constants.SUCCESS);
		result.setMessage(Integer.toString(count));
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/rms/getNewUserCount 今日新增注册军人数
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup RMS
	 * @apiName getNewUserCount
	 * 
	 * @apiSuccess {String} message 返回数量
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 * 
	 */
	@RequestMapping(value = "/getNewUserCount", method = RequestMethod.GET)
	public BaseResult getNewUserCount(HttpServletResponse response) {
		BaseResult result = new BaseResult();
		int count = adminService.getNewUserCount();
		result.setStatusCode(Constants.SUCCESS);
		result.setMessage(Integer.toString(count));
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/rms/getAllUser 获取所有注册军人信息
	 * 
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup rms
	 * @apiName getAllUser
	 * 
	 * @apiParam currentPage 页数
	 * @apiParam pageSize 分页数
	 * 
	 * @apiSuccess {int} total 总条数
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 * @apiSuccess {int} id	ID
	 * @apiSuccess {date} joinTime 提交时间
	 * @apiSuccess {String} officerImage 军人证
	 * @apiSuccess {String} officerNumber 军人编号
	 * @apiSuccess {String} phone 电话
	 * @apiSuccess {String} resident 户籍所在地
	 * @apiSuccess {String} socialId 身份证编号
	 * @apiSuccess {String} userId 用户编号
	 * @apiSuccess {String} userName 用户姓名
	 * 
	 */
	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	public String getAllUser(int currentPage, int pageSize, HttpServletResponse response) {
		BaseListResult result = new BaseListResult();
		try {
			int startPage = (currentPage - 1) * pageSize;
			List<User> users = adminService.getAllUser(startPage, pageSize);
			int total = adminService.getUserCount();
			result.setResults(users);
			result.setStatusCode(Constants.SUCCESS);
			result.setTotal(total);
		} catch (Exception e) {
			result.setStatusCode(Constants.FAILUE);
			log.error("Get All User SQL error = " + e.getMessage());
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/rms/getAllCompany 获取所有注册企业信息
	 * 
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup rms
	 * @apiName getAllCompany
	 * 
	 * @apiParam currentPage 页数
	 * @apiParam pageSize 分页数
	 * 
	 * @apiSuccess {int} total：总条数
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 * @apiSuccess {String} companyRoleId 公司编号
	 * @apiSuccess {String} companyContacts 联系人
	 * @apiSuccess {String} phone 电话
	 * @apiSuccess {String} companyName 企业名称
	 * @apiSuccess {String} account 账号
	 * @apiSuccess {String} password 密码
	 * @apiSuccess {date} joinTime 创建时间
	 * 
	 */
	@RequestMapping(value = "/getAllCompany", method = RequestMethod.GET)
	public String getAllCompany(int currentPage, int pageSize, HttpServletResponse response) {
		BaseListResult result = new BaseListResult();
		try {
			int startPage = (currentPage - 1) * pageSize;
			List<CompanyRole> companys = adminService.getAllCompany(startPage, pageSize);
			int total = adminService.getCompanyCount();
			result.setResults(companys);
			result.setStatusCode(Constants.SUCCESS);
			result.setTotal(total);
		} catch (Exception e) {
			result.setStatusCode(Constants.FAILUE);
			result.setMessage(e.getMessage());
			log.error("Get All Company SQL error = " + e.getMessage());
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {post} jmh/rms/newCompanyRole 录入企业账号信息
	 * 
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup rms
	 * @apiName newCompanyRole
	 * 
	 * @apiParam {String} companyName 企业名称
	 * @apiParam {String} companyContacts 联系人
	 * @apiParam {String} phone 联系号码
	 * @apiParam {String} account 企业账号
	 * @apiParam {String} password 密码
	 * 
	 * @apiSuccess {String} message 返回提示文本
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 * 
	 */
	@RequestMapping(value = "/newCompanyRole", method = RequestMethod.POST)
	public BaseResult newVisitor(Company company, Role role, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		int insetCode = adminService.newCompanyAndRole(company, role);
		if (insetCode == Constants.DB_PROCESS_SUCCESS) {
			result.setStatusCode(Constants.SUCCESS);
			result.setMessage(MsgConstants.NEW_VISITOR_MESSAGE_SUCCEED);
		} else if (insetCode == Constants.DB_PROCESS_FAILUE) {
			result.setStatusCode(Constants.FAILUE);
			result.setMessage(MsgConstants.NEW_VISITOR_MESSAGE_FAILED);
		} else {
			result.setStatusCode(Constants.FAILUE);
			result.setMessage(MsgConstants.NEW_VISITOR_MESSAGE_ISSUED);
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {post} jmh/rms/updateCompanyRole 更新公司账号信息
	 * 
	 * @apiVersion 1.0.0
	 * @apiGroup rms
	 * @apiName updateCompanyRole
	 * 
	 * @apiParam {String} roleId 企业ID
	 * @apiParam {String} companyName 企业名称
	 * @apiParam {String} companyContacts 联系人
	 * @apiParam {String} phone 联系号码
	 * @apiParam {String} account 企业账号
	 * @apiParam {String} password 密码
	 * 
	 * @apiSuccess {String} message 返回执行错误
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 * 
	 */
	@RequestMapping(value = "/updateCompanyRole", method = RequestMethod.POST)
	public String updateCompanyRole(Company company, Role role, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			company.setCompanyId(role.getRoleId());
			int updateCode = adminService.updateCompanyAndRole(company, role);
			if (updateCode == Constants.DB_PROCESS_SUCCESS) {
				result.setStatusCode(Constants.SUCCESS);
			} else {
				result.setMessage(MsgConstants.PROCESS_VISITOR_FAILED);
				result.setStatusCode(Constants.FAILUE);
			}
		} catch (Exception e) {
			result.setMessage(MsgConstants.PROCESS_VISITOR_FAILED);
			result.setStatusCode(Constants.FAILUE);
			log.error("Update Role SQL error = " + e.getMessage());
		}
		WebUtils.responseJson(result, response);
		return null;
	}

}
