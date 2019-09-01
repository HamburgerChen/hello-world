package com.jmh.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmh.dao.WebsiteDataService;
import com.jmh.model.Visitor;
import com.jmh.service.WebsiteService;
import com.jmh.utils.Constants;

@Service("websiteService")
public class WebsiteServiceImpl implements WebsiteService {

	// private static Log log = LogFactory.getLog(WebsiteServiceImpl.class);

	private WebsiteDataService websiteDataService;

	public WebsiteDataService getWebsiteDataService() {
		return websiteDataService;
	}

	@Autowired
	public void setWebsiteDataService(WebsiteDataService websiteDataService) {
		this.websiteDataService = websiteDataService;
	}

	@Override
	public int newVisitor(Visitor visitor) {
		int result = Constants.FAILUE;
		if (visitor == null || visitor.getVisitorName() == null || visitor.getVisitorName().trim().length() == 0
				|| visitor.getPhone() == null || visitor.getPhone().trim().length() == 0) {
			return result;
		}
		if (websiteDataService.insertVisitor(visitor) == 1) {
			result = Constants.SUCCESS;
		}
		return result;
	}

	@Override
	public List<Visitor> getAllVisitor(@Param("startPage") int startPage, @Param("pageSize") int pageSize) {
		List<Visitor> visitors = websiteDataService.getAllVisitor(startPage, pageSize);
		return visitors;
	}

	@Override
	public List<Visitor> getAll() {
		return websiteDataService.getAll();
	}

	@Override
	public String delete(String id) {
		if (websiteDataService.deleteByPrimaryKey(id) == 1) {
			return "删除成功";
		}
		return "删除失败";
	}

	@Override
	public Visitor findById(String id) {
		return websiteDataService.selectByPrimaryKey(id);
	}

	@Override
	public String update(Visitor addInfo) {
		if (websiteDataService.updateByPrimaryKeySelective(addInfo) == 1) {
			return "更新成功";
		}
		return "更新失败";
	}

	@Override
	public void updateVisitor(Visitor visitor) {
		websiteDataService.updateVisitor(visitor);

	}

	@Override
	public void deleteVisitor(String id) {
		websiteDataService.deleteVisitor(id);
	}

	@Override
	public int getVisitorCount() {
		int result = websiteDataService.getUserCount();
		return result;
	}

}
