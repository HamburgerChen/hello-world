/**
 * 
 */
package com.jmh.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;

/**
 * @author chen, weiwei
 * @date 08/06/2019
 *
 */
public final class WebUtils {
	
	private static Log log = LogFactory.getLog(WebUtils.class);

	public static void responseJson(Object object, HttpServletResponse response) {

		try {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(JSON.toJSONString(object));
		} catch (IOException e) {
			log.info("responseJson ERROR: " + e.getMessage());
		}
	}
}
