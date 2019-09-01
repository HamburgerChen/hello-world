package com.jmh.service.impl;

import java.io.IOException;

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

import com.jmh.model.BaseResult;
import com.jmh.model.LoginInfo;
import com.jmh.utils.Constants;
import com.jmh.utils.WebUtils;

public class SessionFilterImpl implements Filter {

	private static Log log = LogFactory.getLog(SessionFilterImpl.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("Session filter starting work...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		BaseResult result = new BaseResult();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		if ((LoginInfo) session.getAttribute(Constants.LOGIN_INFO) == null) {
			session.invalidate();
			result.setStatusCode(Constants.LOGIN_OUT);
			WebUtils.responseJson(result, httpResponse);
			log.info("[Session is invalidate]: DoFilter processing, will login out...");
		} else {
			chain.doFilter(httpRequest, httpResponse);
		}
	}

	@Override
	public void destroy() {
		log.info("Session filter destroying...");
	}

}
