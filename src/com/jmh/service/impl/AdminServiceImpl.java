/**
 * 
 */
package com.jmh.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmh.dao.AdminDataService;
import com.jmh.model.Company;
import com.jmh.model.CompanyRole;
import com.jmh.model.LoginInfo;
import com.jmh.model.Role;
import com.jmh.model.User;
import com.jmh.service.AdminService;

/**
 * @author chen, weiwei
 * @date 08/07/2019
 *
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
	private AdminDataService adminDataService;

	@Autowired
	public void setAdminDataService(AdminDataService adminDataService) {
		this.adminDataService = adminDataService;
	}

	@Override
	public boolean isAuthorized(LoginInfo info) {
		boolean result = adminDataService.isAuthorized(info);
		return result;
	}

	@Override
	public int getUserCount() {
		int result = adminDataService.getUserCount();
		return result;
	}

	@Override
	public int getNewUserCount() {
		int result = adminDataService.getNewUserCount();
		return result;
	}

	@Override
	public List<CompanyRole> getAllCompany(@Param("startPage") int startPage, @Param("pageSize") int pageSize) {
		List<CompanyRole> companys = adminDataService.getAllCompany(startPage, pageSize);
		return companys;
	}

	@Override
	public int newCompanyAndRole(Company company, Role role) {
		String roleId = adminDataService.newCompanyId();
		company.setCompanyId(roleId);
		int newCompanyCode = adminDataService.newCompany(company);
		role.setRoleId(roleId);
		int newRoleCode = adminDataService.newRole(role);
		return newRoleCode * newCompanyCode;
	}

	@Override
	public int updateCompanyAndRole(Company company, Role role) {
		int updateCompanyCode = adminDataService.updateCompany(company);
		int updateRoleCode = adminDataService.updateRole(role);
		return updateCompanyCode * updateRoleCode;
	}

	@Override
	public List<User> getAllUser(@Param("startPage") int startPage, @Param("pageSize") int pageSize) {
		List<User> users = adminDataService.getAllUser(startPage, pageSize);
		return users;
	}

	@Override
	public int getCompanyCount() {
		int result = adminDataService.getCompanyCount();
		return result;
	}

	@Override
	public int newUser(User user) {
		String userId = adminDataService.newUserId();
		user.setUserId(userId);
		int newUserCode = adminDataService.newUser(user);
		return newUserCode;
	}

	@Override
	public String bindPhone(String telephone, String openId) {
		adminDataService.bindPhone();
		return telephone;
	}

	@Override
	public CompanyRole companyLogin(LoginInfo info) {
		CompanyRole result = adminDataService.companyLogin(info);
		return result;
	}

}
