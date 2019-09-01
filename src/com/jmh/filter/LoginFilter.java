/**
 * 
 */
package com.jmh.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jmh.model.LoginInfo;
import com.jmh.utils.Constants;
import com.jmh.utils.WebUtils;

/**
 * Servlet Filter implementation class LoginFileter
 */

public class LoginFilter implements Filter {
	
	private static Log log = LogFactory.getLog(LoginFilter.class);
	
	private List<String> uriLists = new ArrayList<String>();
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		log.info("LoginFilter过滤器启动");
		String noUri = fConfig.getInitParameter("noUri");  //从web.xml读取配置
		String[] uriArrays = noUri.split(";"); 
		for(String uri: uriArrays){
			uriLists.add(uri);
		}
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("LoginFilter过滤器执行");
		HttpServletRequest servletrequest = (HttpServletRequest) request;
		HttpServletResponse ServletResponse = (HttpServletResponse) response;
		HttpSession session = servletrequest.getSession();
		String uri = servletrequest.getRequestURI(); //获取地址
		log.info("uri: " + uri);
		//需要登录验证处理
		if(needLoginValidate(uri)){
			LoginInfo user = (LoginInfo) session.getAttribute(Constants.LOGIN_INFO); // 获取用户名密码
			if (user == null || user.equals("")) { // 如果没有登录就跳转到登录界面
				log.info("未登录账号");
				//TODO:ServletResponse.sendRedirect("/IntegralManager_v1.0/jsp/login.jsp");
				WebUtils.responseJson("no login", ServletResponse);
			} else {
				chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
		
	}
	public void destroy() {
		log.info("LoginFilter过滤器销毁");
	}
 
 
	/**
	 * 请求uri是否需要登录验证
	 * @return false:需要验证 true:不需要验证
	 */
	public boolean needLoginValidate(String uri) {
		boolean isNeedValidate = false;
		int index = uri.indexOf("?");
		if (index != -1) {
			uri = uri.substring(0, index);
		}
		for (String u : uriLists) {  
			if (uri.endsWith(u)) {//不需要登录验证的网页
				isNeedValidate = true;     
				break;
			}
		}
		return isNeedValidate;
	}

 
}