package com.jmh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jmh.model.BaseJobProvidedResult;
import com.jmh.model.BaseJobWantedResult;
import com.jmh.model.BaseListResult;
import com.jmh.model.BaseLoginResult;
import com.jmh.model.BaseResult;
import com.jmh.model.CheckCodeResult;
import com.jmh.model.CompanyRole;
import com.jmh.model.JobProvidedInfo;
import com.jmh.model.JobWantedInfo;
import com.jmh.model.LoginInfo;
import com.jmh.model.User;
import com.jmh.model.WeChatUserInfo;
import com.jmh.model.WeChatUserResult;
import com.jmh.service.AdminService;
import com.jmh.service.ConfigService;
import com.jmh.service.WeChatService;
import com.jmh.utils.Constants;
import com.jmh.utils.MsgConstants;
import com.jmh.utils.SMSAliUtils;
import com.jmh.utils.WeChatUtils;
import com.jmh.utils.WebUtils;

@RestController()
@RequestMapping("/weChat")
public class WeChatController {
	private static Log log = LogFactory.getLog(WeChatController.class);

	private WeChatService weChatService;
	private AdminService adminService;
	private ConfigService configService;

	@Autowired
	public void setWeChatService(WeChatService weChatService) {
		this.weChatService = weChatService;
	}

	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@Autowired
	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	@RequestMapping(value = "/checkSignature", method = RequestMethod.GET)
	public void checkSignature(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = resp.getWriter();
		if (WeChatUtils.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
			out.close();
			out = null;
		}
	}

	@RequestMapping(value = "/checkSignature", method = RequestMethod.POST)
	@ResponseBody
	public String getWeiXinMessage(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String respXml = null;
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
 
		// 接收参数微信加密签名、 时间戳、随机数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		PrintWriter out = response.getWriter();
		// 请求校验
		if (WeChatUtils.checkSignature(signature, timestamp, nonce)) {
		// 调用核心服务类接收处理请求
			respXml = weChatService.processRequest(request);
			log.info("respXml: \n" + respXml);
			if(respXml!=null){
				response.setContentType("text/html;charset=UTF-8");
				out.print(respXml);
			}
		}
		out.close();
		out = null;
		return null;
	}

	/**
	 * @api {post} jmh/weChat/login 公众号登录
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup weChat
	 * @apiName login
	 * 
	 * @apiParam {String} loginName 登录姓名
	 * @apiParam {String} loginPwd 登录密码
	 * 
	 * @apiSuccess {String} message 返回错误消息
	 * @apiSuccess {int} statusCode 0/1 代表无/有错误状态
	 * @apiSuccess {String} companyRoleId 等同companyId
	 * @apiSuccess {String} companyName 企业名称
	 * @apiSuccess {String} officialImage 头像路径
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public BaseResult login(LoginInfo info, HttpServletRequest request, HttpServletResponse response) {
		BaseLoginResult result = new BaseLoginResult();
		HttpSession session = request.getSession();
		session.setAttribute(Constants.LOGIN_INFO, info);
		CompanyRole compRole = adminService.companyLogin(info);
		if (compRole != null) {
			result.setCompRole(compRole);
			result.setStatusCode(Constants.SUCCESS);
		} else {
			result.setStatusCode(Constants.FAILUE);
			result.setMessage(MsgConstants.RMS_NO_AUTHORIZED);
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/weChat/getWechatUserInfo 获取微信用户基本信息
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup weChat
	 * @apiName getUserInfo
	 * 
	 * @apiSuccess {String} message 返回错误消息
	 * @apiSuccess {int} statusCode 0/1 代表无/有错误状态
	 * @apiSuccess {String} openid 微信用户openId
	 * @apiSuccess {String} nickname 微信用户openId
	 * @apiSuccess {int} sex 性别 1男2女
	 * @apiSuccess {String} language 语言
	 * @apiSuccess {String} city 市
	 * @apiSuccess {String} province 省
	 * @apiSuccess {String} country 国家
	 * @apiSuccess {String} headimgurl 头像路径
	 * @apiSuccess {date} subscribe_time
	 * 
	 * 
	 */
	@RequestMapping(value = "/getWechatUserInfo", method = RequestMethod.GET)
	public String getWechatUserInfo(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		log.info("entering getWechatUserInfo function...");
		WeChatUserResult result = new WeChatUserResult();
		// 首先判断一下session中，是否有保存着的当前用户的信息，有的话，就不需要进行重复请求信息
		WeChatUserInfo weChatUser = (WeChatUserInfo) session.getAttribute("currentUserInfo");
		if (session.getAttribute("currentUserInfo") != null && weChatUser.getOpenid() != null) {
			log.info("currentUserInfo is logon...");
			log.info("weChatUser openid: " + weChatUser.getOpenid());
		} else {
			/**
			 * 进行获取openId，必须的一个参数，这个是当进行了授权页面的时候，再重定向了我们自己的一个页面的时候，
			 * 会在request页面中，新增这个字段信息，要结合这个ProjectConst.Get_WEIXINPAGE_Code这个常量思考
			 */
			String code = request.getParameter("code");
			log.info("code = " + code);
			try {
				// 得到当前用户的信息(具体信息就看weixinUser这个javabean)
				String openId = WeChatUtils.oauth2GetOpenid(code);
				if (openId == null) {
					result.setStatusCode(Constants.FAILUE);
					WebUtils.responseJson(result, response);
					return null;
				}
				weChatUser = WeChatUtils.getWeChatUserInfo(configService.getConfigValue(Constants.ACCESS_TOKEN_KEY),
						openId);
				// 将获取到的用户信息，放入到session中
				session.setAttribute("currentUserInfo", weChatUser);
				result.setStatusCode(Constants.SUCCESS);
			} catch (Exception e) {
				result.setStatusCode(Constants.FAILUE);
				result.setMessage(e.getMessage());
				log.error(e.getMessage());
			}
		}
		User user = new User();
		user.setOpenId(weChatUser.getOpenid());
		if (!adminService.isExistedUser(weChatUser.getOpenid())) {
			log.info("new user for openId = " + weChatUser.getOpenid());
			adminService.newUser(user);
		}
		result.setWeChatUserInfo(weChatUser);
		WebUtils.responseJson(result, response);
		log.info("leaving getWechatUserInfo function...");
		return null;
	}

	/**
	 * @api {get} jmh/weChat/loginOut 公众号退出登录
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup weChat
	 * @apiName loginOut
	 * 
	 * @apiSuccess {String} message 返回错误消息
	 * @apiSuccess {int} statusCode 3登出成功
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
	 * @api {get} jmh/weChat/sms 验证码发送
	 * 
	 * @apiVersion 1.0.0
	 * @apiGroup weChat
	 * @apiName sms
	 * 
	 * @apiParam {String} telephone 手机号码
	 * 
	 * @apiSuccess {String} message 返回执行错误
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 * @apiSuccess {int} telephone 手机号码
	 * @apiSuccess {int} checkCode 6位验证码(5分钟失效)
	 * 
	 */
	@RequestMapping(value = "/sms", method = RequestMethod.GET)
	public String smsValidate(String telephone, HttpServletResponse response, HttpServletRequest request) {
		CheckCodeResult result = new CheckCodeResult();
		final HttpSession httpSession = request.getSession();
		try {
			String checkCode = SMSAliUtils.createRandomNumber();
			httpSession.setAttribute("checkCode", checkCode);
			result = weChatService.sendSMS(telephone, checkCode);
			// TimerTask实现5分钟后从session中删除checkCode
			final Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					httpSession.removeAttribute("checkCode");
					System.out.println("checkCode删除成功");
					timer.cancel();
				}
			}, Constants.TIME_MINITES_FIVE);
		} catch (Exception e) {
			log.error("SMS Validate error = " + e.getMessage());
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/weChat/getPhone 获取手机号码
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup weChat
	 * @apiName getPhone
	 * 
	 * @apiParam {String} openId 微信openId
	 * 
	 * @apiSuccess {String} message 成功返回phone，失败返回错误
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 * 
	 */
	@RequestMapping(value = "/getPhone", method = RequestMethod.GET)
	public BaseResult getPhone(String openId, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		String phone = weChatService.getPhone(openId);
		if (phone != null) {
			result.setStatusCode(Constants.SUCCESS);
			result.setMessage(phone);
		} else {
			result.setStatusCode(Constants.FAILUE);
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/weChat/bindPhone 绑定手机号码
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup weChat
	 * @apiName bindPhone
	 * 
	 * @apiParam {String} openId 微信openId
	 * @apiParam {String} telephone 联系电话
	 * 
	 * @apiSuccess {String} message 成功返回phone，失败返回错误
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 * 
	 */
	@RequestMapping(value = "/bindPhone", method = RequestMethod.GET)
	public BaseResult bindPhone(String openId, String telephone, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		int updateCode = Constants.DB_PROCESS_FAILUE;
		if (openId == null) {
			result.setStatusCode(Constants.FAILUE);
			result.setMessage("openId参数等于null");
		}

		if (!adminService.isExistedUser(openId)) {
			User user = new User();
			user.setOpenId(openId);
			user.setPhone(telephone);
			log.info("new user for openId = " + openId);
			updateCode = adminService.newUser(user);
		} else {
			updateCode = adminService.bindPhone(telephone, openId);
		}
		if (updateCode == Constants.DB_PROCESS_SUCCESS) {
			result.setStatusCode(Constants.SUCCESS);
			result.setMessage(telephone);
		} else {
			result.setStatusCode(Constants.FAILUE);
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/weChat/check?checkCode=666888 验证码验证
	 * 
	 * @apiVersion 1.0.0
	 * @apiGroup weChat
	 * @apiName check
	 * 
	 * @apiParam {String} checkCode 6位验证码
	 * 
	 * @apiSuccess {String} message 返回执行错误
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 */
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String checkCode(String checkCode, HttpServletResponse response, HttpServletRequest request) {
		BaseResult result = new BaseResult();
		final HttpSession httpSession = request.getSession();
		try {
			boolean isCorrect = httpSession.getAttribute("checkCode").equals(checkCode);
			if (isCorrect) {
				result.setStatusCode(Constants.SUCCESS);
			} else {
				result.setStatusCode(Constants.FAILUE);
				result.setMessage(MsgConstants.CHECK_CODE_INCORRECT);
			}
		} catch (Exception e) {
			log.error("SMS Validate error = " + e.getMessage());
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/weChat/getAllJobPvdInfo 获取所有职位信息
	 * 
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup weChat
	 * @apiName getAllJobPvdInfo
	 * 
	 * @apiParam currentPage 页数
	 * @apiParam pageSize 分页数
	 * 
	 * 
	 * @apiSuccess {String} statusCode 0 代表无错误 1代表有错误状态
	 * @apiSuccess {String} total 总数量
	 * @apiSuccess {String} jobId 职位编号
	 * @apiSuccess {String} jobName 职位名称
	 * @apiSuccess {String} salaryRange 薪资范围
	 * @apiSuccess {String} location 工作地点
	 * @apiSuccess {String} companyId 公司编号
	 * @apiSuccess {String} companyName 公司名称
	 * @apiSuccess {String} jobDesc 职位描述
	 * @apiSuccess {Char} eduKey 学历类型
	 * @apiSuccess {String} eduValue 学历名称
	 * @apiSuccess {Char} industryKey 行业类型
	 * @apiSuccess {String} industryValue 行业名称
	 * @apiSuccess {Char} compSizeKey 公司规模类型
	 * @apiSuccess {String} compSizeValue 公司规模名称
	 * @apiSuccess {Char} jobExpKey 工作经验类型
	 * @apiSuccess {String} jobExpValue 工作经验名称
	 * @apiSuccess {date} updateTime 创建/修改时间
	 * @apiSuccess {String} updateBy 创建人/修改人
	 * 
	 */
	@RequestMapping(value = "/getAllJobPvdInfo", method = RequestMethod.GET)
	public String getAllJobPvdInfo(int currentPage, int pageSize, HttpServletResponse response) {
		BaseListResult result = new BaseListResult();
		try {
			int startPage = (currentPage - 1) * pageSize;
			List<JobProvidedInfo> jobProvidedInfos = weChatService.getAllJobProvidedInfo(startPage, pageSize);
			int total = weChatService.getAllJobProvidedCount();
			result.setResults(jobProvidedInfos);
			result.setStatusCode(Constants.SUCCESS);
			result.setTotal(total);
		} catch (Exception e) {
			result.setStatusCode(Constants.FAILUE);
			log.error("SQL error = " + e.getMessage());
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/weChat/getJobPvdByJobId 获取职位详情
	 * 
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup weChat
	 * @apiName getJobProvidedInfo
	 * 
	 * @apiParam jobId 职位编号
	 * 
	 * @apiSuccess {String} jobId 职位编号
	 * @apiSuccess {String} jobName 职位名称
	 * @apiSuccess {String} salaryRange 薪资范围
	 * @apiSuccess {String} location 工作地点
	 * @apiSuccess {String} companyId 公司编号
	 * @apiSuccess {String} companyName 公司名称
	 * @apiSuccess {String} jobDesc 职位描述
	 * @apiSuccess {Char} eduKey 学历类型
	 * @apiSuccess {String} eduValue 学历名称
	 * @apiSuccess {Char} industryKey 行业类型
	 * @apiSuccess {String} industryValue 行业名称
	 * @apiSuccess {Char} compSizeKey 公司规模类型
	 * @apiSuccess {String} compSizeValue 公司规模名称
	 * @apiSuccess {Char} jobExpKey 工作经验类型
	 * @apiSuccess {String} jobExpValue 工作经验名称
	 * @apiSuccess {date} updateTime 创建/修改时间
	 * @apiSuccess {String} updateBy 创建人/修改人
	 * 
	 */
	@RequestMapping(value = "/getJobPvdByJobId", method = RequestMethod.GET)
	public String getJobPvdByJobId(String jobId, HttpServletResponse response) {
		BaseJobProvidedResult result = new BaseJobProvidedResult();
		try {
			JobProvidedInfo jobProvidedInfo = weChatService.getJobProvidedInfo(jobId);
			result.setJobProvidedInfo(jobProvidedInfo);
			result.setStatusCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setStatusCode(Constants.FAILUE);
			log.error("SQL error = " + e.getMessage());
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/weChat/getAllJobPvdByCompId 获取本公司所有职位信息
	 * 
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup weChat
	 * @apiName getAllJobPvdByCompId
	 * 
	 * 
	 * @apiParam companyId 页数
	 * @apiParam currentPage 页数
	 * @apiParam pageSize 分页数
	 * 
	 * 
	 * @apiSuccess {String} statusCode 0 代表无错误 1代表有错误状态
	 * @apiSuccess {String} total 总数量
	 * @apiSuccess {String} jobId 职位编号
	 * @apiSuccess {String} jobName 职位名称
	 * @apiSuccess {String} salaryRange 薪资范围
	 * @apiSuccess {String} location 工作地点
	 * @apiSuccess {String} companyId 公司编号
	 * @apiSuccess {String} companyName 公司名称
	 * @apiSuccess {String} jobDesc 职位描述
	 * @apiSuccess {Char} eduKey 学历类型
	 * @apiSuccess {String} eduValue 学历名称
	 * @apiSuccess {Char} industryKey 行业类型
	 * @apiSuccess {String} industryValue 行业名称
	 * @apiSuccess {Char} compSizeKey 公司规模类型
	 * @apiSuccess {String} compSizeValue 公司规模名称
	 * @apiSuccess {Char} jobExpKey 工作经验类型
	 * @apiSuccess {String} jobExpValue 工作经验名称
	 * @apiSuccess {date} updateTime 创建/修改时间
	 * @apiSuccess {String} updateBy 创建人/修改人
	 * 
	 */
	@RequestMapping(value = "/getAllJobPvdByCompId", method = RequestMethod.GET)
	public String getAllJobPvdByCompId(String companyId, int currentPage, int pageSize, HttpServletResponse response) {
		BaseListResult result = new BaseListResult();
		try {
			int startPage = (currentPage - 1) * pageSize;
			List<JobProvidedInfo> jobProvidedInfos = weChatService.getAllJobPvdInfoByCompId(companyId, startPage,
					pageSize);
			int total = weChatService.getJobProvidedCompCount(companyId);
			result.setResults(jobProvidedInfos);
			result.setStatusCode(Constants.SUCCESS);
			result.setTotal(total);
		} catch (Exception e) {
			result.setStatusCode(Constants.FAILUE);
			log.error("SQL error = " + e.getMessage());
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/weChat/newJobProvidedInfo 创建职位信息
	 * 
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup weChat
	 * @apiName newJobProvidedInfo
	 * 
	 * @apiParam {String} jobName 职位名称
	 * @apiParam {String} salaryRange 薪资范围
	 * @apiParam {String} locationCode 地址代码
	 * @apiParam {String} location 工作地点
	 * @apiParam {String} companyId 公司编号
	 * @apiParam {String} companyName 公司名称
	 * @apiParam {String} jobDesc 职位描述
	 * @apiParam {Char} eduKey 学历类型
	 * @apiParam {Char} industryKey 行业类型
	 * @apiParam {Char} compSizeKey 公司规模类型
	 * @apiParam {Char} jobExpKey 工作经验类型
	 * @apiParam {String} updateBy 创建人/修改人
	 * 
	 * @apiSuccess {String} message 返回提示文本
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 */
	@RequestMapping(value = "/newJobProvidedInfo", method = RequestMethod.GET)
	public BaseResult newJobProvidedInfo(JobProvidedInfo jobInfo, HttpServletResponse response) {
		BaseJobProvidedResult result = new BaseJobProvidedResult();
		String jobId = weChatService.newJobProvidedInfo(jobInfo);
		if (jobId != null) {
			jobInfo.setJobId(jobId);
			result.setStatusCode(Constants.SUCCESS);
			result.setJobProvidedInfo(jobInfo);
		} else {
			result.setStatusCode(Constants.FAILUE);
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/weChat/updateJobProvidedInfo 编辑职位信息
	 * 
	 * @apiVersion 1.0.0
	 * @apiGroup weChat
	 * @apiName updateJobProvidedInfo
	 * 
	 * @apiParam {String} jobId 职位编号
	 * @apiParam {String} jobName 职位名称
	 * @apiParam {String} salaryRange 薪资范围
	 * @apiParam {String} location 工作地点
	 * @apiParam {String} companyId 公司编号
	 * @apiParam {String} companyName 公司名称
	 * @apiParam {String} jobDesc 职位描述
	 * @apiParam {Char} eduKey 学历类型
	 * @apiParam {Char} industryKey 行业类型
	 * @apiParam {Char} compSizeKey 公司规模类型
	 * @apiParam {Char} jobExpKey 工作经验类型
	 * @apiParam {String} updateBy 创建人/修改人
	 * 
	 * @apiSuccess {String} message 返回执行错误
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 * 
	 */
	@RequestMapping(value = "/updateJobProvidedInfo", method = RequestMethod.GET)
	public String updateJobProvidedInfo(JobProvidedInfo jobInfo, HttpServletResponse response) {
		BaseJobProvidedResult result = new BaseJobProvidedResult();
		try {
			int updateCode = weChatService.updateJobProvidedInfo(jobInfo);
			if (updateCode == Constants.DB_PROCESS_SUCCESS) {
				result.setStatusCode(Constants.SUCCESS);
				result.setJobProvidedInfo(jobInfo);
			} else {
				result.setStatusCode(Constants.FAILUE);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode(Constants.FAILUE);
			log.error("SQL error = " + e.getMessage());
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/weChat/deleteJobProvidedInfo 删除职位信息
	 * 
	 * @apiVersion 1.0.0
	 * @apiGroup weChat
	 * @apiName deleteJobProvidedInfo
	 * 
	 * @apiParam {String} jobId 职位编号
	 * 
	 * @apiSuccess {String} message 返回执行错误
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 */
	@RequestMapping(value = "/deleteJobProvidedInfo", method = RequestMethod.GET)
	public String deleteJobProvidedInfo(String jobId, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			int detelCode = weChatService.deleteJobProvidedInfo(jobId);
			if (detelCode == Constants.DB_PROCESS_SUCCESS) {
				result.setStatusCode(Constants.SUCCESS);
			} else {
				result.setStatusCode(Constants.FAILUE);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode(Constants.FAILUE);
			log.error("SQL error = " + e.getMessage());
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/weChat/getAllJobWantedInfo 获取所有求职信息
	 * 
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup weChat
	 * @apiName getAllJobWantedInfo
	 * 
	 * @apiParam currentPage 页数
	 * @apiParam pageSize 分页数
	 * 
	 * 
	 * @apiSuccess {String} statusCode 0 代表无错误 1代表有错误状态
	 * @apiSuccess {String} total 总数量
	 * @apiSuccess {String} wantedId 求职编号
	 * @apiSuccess {String} wantedName 求职者姓名
	 * @apiSuccess {char} male 求职者性别
	 * @apiSuccess {int} age 求职者年龄
	 * @apiSuccess {char} eduKey 学历类型
	 * @apiSuccess {String} serveExp 服役经历
	 * @apiSuccess {String} wantedSalary 期望薪资
	 * @apiSuccess {String} wantedPlace 期望工作地点
	 * @apiSuccess {String} wantedJob1 期望职位1
	 * @apiSuccess {String} wantedJob2 期望职位2
	 * @apiSuccess {String} advantage1 优势1
	 * @apiSuccess {String} advantage2 优势2
	 * @apiSuccess {String} introduce 个人介绍
	 * @apiSuccess {String} updateBy 创建/修改人
	 * 
	 */
	@RequestMapping(value = "/getAllJobWantedInfo", method = RequestMethod.GET)
	public String getAllJobWantedInfo(int currentPage, int pageSize, HttpServletResponse response) {
		BaseListResult result = new BaseListResult();
		try {
			int startPage = (currentPage - 1) * pageSize;
			List<JobWantedInfo> jobWantedInfos = weChatService.getAllJobWantedInfo(startPage, pageSize);
			int total = weChatService.getAllJobWantedCount();
			result.setResults(jobWantedInfos);
			result.setStatusCode(Constants.SUCCESS);
			result.setTotal(total);
		} catch (Exception e) {
			result.setStatusCode(Constants.FAILUE);
			log.error("Get All Job Wanted SQL error = " + e.getMessage());
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/weChat/getJobWantedById 获取求职详情
	 * 
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup weChat
	 * @apiName getJobWantedById
	 * 
	 * @apiParam wantedId 求职编号
	 * 
	 * @apiSuccess {String} statusCode 0 代表无错误 1代表有错误状态
	 * @apiSuccess {String} total 总数量
	 * @apiSuccess {String} wantedId 求职编号
	 * @apiSuccess {String} wantedName 求职者姓名
	 * @apiSuccess {char} male 求职者性别
	 * @apiSuccess {int} age 求职者年龄
	 * @apiSuccess {char} eduKey 学历类型
	 * @apiSuccess {String} serveExp 服役经历
	 * @apiSuccess {String} wantedSalary 期望薪资
	 * @apiSuccess {String} wantedPlace 期望工作地点
	 * @apiSuccess {String} wantedJob1 期望职位1
	 * @apiSuccess {String} wantedJob2 期望职位2
	 * @apiSuccess {String} advantage1 优势1
	 * @apiSuccess {String} advantage2 优势2
	 * @apiSuccess {String} introduce 个人介绍
	 * @apiSuccess {String} updateBy 创建/修改人
	 * 
	 */
	@RequestMapping(value = "/getJobWantedById", method = RequestMethod.GET)
	public String getJobWantedById(String wantedId, HttpServletResponse response) {
		BaseJobWantedResult result = new BaseJobWantedResult();
		try {
			JobWantedInfo jobWantedInfo = weChatService.getJobWantedInfo(wantedId);
			result.setJobWantedInfo(jobWantedInfo);
			result.setStatusCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setStatusCode(Constants.FAILUE);
			log.error("SQL error = " + e.getMessage());
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/weChat/newJobWantedInfo 创建求职信息
	 * 
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup weChat
	 * @apiName newJobWantedInfo
	 * 
	 * @apiParam {String} wantedName 求职者姓名
	 * @apiParam {char} male 求职者性别M/F
	 * @apiParam {int} age 求职者年龄
	 * @apiParam {char} edukey 学历类型
	 * @apiParam {String} serveExp 服役经历
	 * @apiParam {String} wantedSalary 期望薪资
	 * @apiParam {String} wantedPlace 期望工作地点
	 * @apiParam {String} wantedJob1 期望职位1
	 * @apiParam {String} wantedJob2 期望职位2
	 * @apiParam {String} advantage1 优势1
	 * @apiParam {String} advantage2 优势2
	 * @apiParam {String} introduce 个人介绍
	 * @apiParam {String} updateBy 创建/修改人
	 * 
	 * @apiSuccess {String} message 返回提示文本
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 * @apiSuccess {String} wantedId 求职编号
	 * @apiSuccess {String} wantedName 求职者姓名
	 * @apiSuccess {char} male 求职者性别
	 * @apiSuccess {int} age 求职者年龄
	 * @apiSuccess {char} eduKey 学历类型
	 * @apiSuccess {String} wantedSalary 期望薪资
	 * @apiSuccess {String} serveExp 服役经历
	 * @apiSuccess {String} wantedSalary 期望薪资
	 * @apiSuccess {String} wantedPlace 期望工作地点
	 * @apiSuccess {String} wantedJob1 期望职位1
	 * @apiSuccess {String} wantedJob2 期望职位2
	 * @apiSuccess {String} advantage1 优势1
	 * @apiSuccess {String} advantage2 优势2
	 * @apiSuccess {String} introduce 个人介绍
	 * @apiSuccess {String} updateBy 创建/修改人
	 */
	@RequestMapping(value = "/newJobWantedInfo", method = RequestMethod.GET)
	public BaseResult newJobWantedInfo(JobWantedInfo wantedInfo, HttpServletResponse response) {
		BaseJobWantedResult result = new BaseJobWantedResult();
		String wantedId = weChatService.newJobWantedInfo(wantedInfo);
		if (wantedId != null) {
			wantedInfo.setWantedId(wantedId);
			result.setJobWantedInfo(wantedInfo);
			result.setStatusCode(Constants.SUCCESS);
		} else {
			result.setStatusCode(Constants.FAILUE);
		}
		WebUtils.responseJson(result, response);
		return null;
	}

}
