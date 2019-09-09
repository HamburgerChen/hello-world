/**
 * 
 */
package com.jmh.service;

import java.util.List;

import com.jmh.model.Company;
import com.jmh.model.CompanyRole;
import com.jmh.model.LoginInfo;
import com.jmh.model.Role;
import com.jmh.model.User;

/**
 * @author chen, weiwei
 * @date 08/07/2019
 * 
 */
public interface AdminService {

	boolean isAuthorized(LoginInfo info);
	int getUserCount();
	int getNewUserCount();
	List<User> getAllUser(int startPage, int pageSize);
	List<CompanyRole> getAllCompany(int startPage, int pageSize);
	int newCompanyAndRole(Company company, Role role);
	int getCompanyCount();
	int updateCompanyAndRole(Company company, Role role);
	int newUser(User user);
	int bindPhone(String telephone, String openId);
	CompanyRole companyLogin(LoginInfo info);
	boolean isExistedUser(String openId);
}
