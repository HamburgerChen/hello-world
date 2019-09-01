package com.jmh.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jmh.model.BaseListResult;
import com.jmh.model.BaseResult;
import com.jmh.model.Visitor;
import com.jmh.service.WebsiteService;
import com.jmh.utils.Constants;
import com.jmh.utils.MsgConstants;
import com.jmh.utils.WebUtils;

@RestController()
@RequestMapping("/website")
public class WebsiteController {

	private static Log log = LogFactory.getLog(WebsiteController.class);

	private WebsiteService websiteService;

	@Autowired
	public void setBaseService(WebsiteService websiteService) {
		this.websiteService = websiteService;
	}

	/**
	 * @api {get} jmh/website/newVisitor 新建游客信息（免费加盟）
	 * 
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup website
	 * @apiName newVisitor
	 * 
	 * @apiParam {String} visitorName 游客姓名
	 * @apiParam {String} phone 联系方式
	 * @apiParam {String} visitorStatus 默认为空 
	 * @apiParam {String} visitorType 企业/导师
	 * 
	 * @apiSuccess {String} message 返回提示文本
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 * 
	 */
	@RequestMapping(value = "/newVisitor", method = RequestMethod.GET)
	public BaseResult newVisitor(Visitor visitor, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		int statusCode = websiteService.newVisitor(visitor);
		result.setStatusCode(statusCode);
		if (statusCode == Constants.SUCCESS) {
			result.setMessage(MsgConstants.NEW_VISITOR_MESSAGE_SUCCEED);
		} else if (statusCode == Constants.FAILUE) {
			result.setMessage(MsgConstants.NEW_VISITOR_MESSAGE_FAILED);
		} else {
			result.setMessage(MsgConstants.NEW_VISITOR_MESSAGE_ISSUED);
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/website/getAllVisitor 获取所有登记游客信息
	 * 
	 * @apiVersion 1.0.0
	 * 
	 * @apiGroup website
	 * @apiName getAllVisitor
	 * 
	 * @apiParam currentPage 页数
	 * @apiParam pageSize 分页数
	 * 
	 * 
	 * @apiSuccess {String} statusCode 0 代表无错误 1代表有错误状态
	 * @apiSuccess {String} total 总数量
	 * @apiSuccess {String} visitorName 联系人
	 * @apiSuccess {String} phone 联系电话
	 * @apiSuccess {String} visitorTypeName 身份
	 * @apiSuccess {date} sumbitTime 提交时间
	 * @apiSuccess {String} visitorStatus 状态
	 * 
	 */
	@RequestMapping(value = "/getAllVisitor", method = RequestMethod.GET)
	public String getAllVisitor(int currentPage, int pageSize, HttpServletResponse response) {
		BaseListResult result = new BaseListResult();
		try {
			int startPage = (currentPage - 1) * pageSize;
			List<Visitor> visitors = websiteService.getAllVisitor(startPage, pageSize);
			int total = websiteService.getVisitorCount();
			result.setResults(visitors);
			result.setStatusCode(Constants.SUCCESS);
			result.setTotal(total);
		} catch (Exception e) {
			result.setStatusCode(Constants.FAILUE);
			log.error("Get All Visitor SQL error = " + e.getMessage());
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {post} jmh/website/updateVisitor 更新游客信息
	 * 
	 * @apiVersion 1.0.0
	 * @apiGroup website
	 * @apiName updateVisitor
	 * 
	 * @apiParam {String} id 注意: 非页面序号
	 * @apiParam {String} visitorName 游客姓名
	 * @apiParam {String} phone 联系方式
	 * @apiParam {String} visitorStatus 默认为空 
	 * @apiParam {String} visitorType 企业/导师
	 *  
	 * @apiSuccess {String} message 返回执行错误
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 * 
	 */
	@RequestMapping(value = "/updateVisitor", method = RequestMethod.POST)
	public String updateVisitor(Visitor visitor, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			websiteService.updateVisitor(visitor);
			result.setStatusCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setMessage(MsgConstants.PROCESS_VISITOR_FAILED);
			result.setStatusCode(Constants.FAILUE);
			log.error("Update Visitor SQL error = " + e.getMessage());
		}
		WebUtils.responseJson(result, response);
		return null;
	}

	/**
	 * @api {get} jmh/website/deleteVisitor 删除游客信息
	 * 
	 * @apiVersion 1.0.0
	 * @apiGroup website
	 * @apiName deleteVisitor
	 * 
	 * @apiParam {String} id 注意: 非页面序号
	 *  
	 * @apiSuccess {String} message 返回执行错误
	 * @apiSuccess {int} statusCode 0 代表无错误 1代表有错误状态
	 * 
	 * @apiSuccessExample Success-Response: { "message"
	 * 						:"", "statusCode": 0 }
	 */
	@RequestMapping(value = "/deleteVisitor", method = RequestMethod.GET)
	public String deleteVisitor(String id, HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			websiteService.deleteVisitor(id);
			result.setStatusCode(Constants.SUCCESS);
		} catch (Exception e) {
			result.setMessage(MsgConstants.PROCESS_VISITOR_FAILED);
			result.setStatusCode(Constants.FAILUE);
			log.error("Delete Visitor SQL error = " + e.getMessage());
		}
		WebUtils.responseJson(result, response);
		return null;
	}
	
}
