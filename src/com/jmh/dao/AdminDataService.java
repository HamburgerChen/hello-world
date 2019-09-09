/**
 * 
 */
package com.jmh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

public interface AdminDataService {
	boolean isAuthorized(LoginInfo info);

	int getUserCount();

	int getNewUserCount();

	List<User> getAllUser();

	List<CompanyRole> getAllCompany(@Param("startPage") int startPage, @Param("pageSize") int pageSize);

	List<User> getAllUser(@Param("startPage") int startPage, @Param("pageSize") int pageSize);

	int getCompanyCount();

	int newCompany(Company company);
	
	String newCompanyId();

	int updateRole(Role role);

	int updateCompany(Company company);

	int newRole(Role role);
	
	int newUser(User user);

	String newUserId();

	int bindPhone(@Param("telephone") String telephone, @Param("openId") String openId);

	CompanyRole companyLogin(LoginInfo info);

	boolean isExistedUser(String openId);
}
