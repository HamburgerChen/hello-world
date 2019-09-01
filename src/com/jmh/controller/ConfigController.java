/**
 * 
 */
package com.jmh.controller;

import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jmh.service.ConfigService;
import com.jmh.utils.Constants;
import com.jmh.utils.WeChatUtils;

/**
 * @author chen, weiwei
 * @date 08/24/2019
 *
 */
@Controller
@RequestMapping(value = "/timer")
public class ConfigController {
	
	private static Log log = LogFactory.getLog(ConfigController.class);

	private ConfigService configService;

	@Autowired
	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}
	
	private Timer timer = new Timer(true);
	
	@PostConstruct
	public void setToken() {
		if (null == timer) {
			timer = new Timer(true);
		}
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					log.info("processing update access token now...");
					configService.updateConfigInfo(Constants.ACCESS_TOKEN_KEY, 
							WeChatUtils.getAccessToken().getToken(), WeChatUtils.getAccessToken().getExpiresIn());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 0, 3600 * 1000);
	}
	
}
