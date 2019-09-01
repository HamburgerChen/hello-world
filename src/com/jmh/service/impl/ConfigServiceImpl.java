/**
 * 
 */
package com.jmh.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmh.dao.ConfigDataService;
import com.jmh.service.ConfigService;

/**
 * @author chen, weiwei
 * @date 08/28/2019
 *
 */

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	
	private ConfigDataService configDataService;

	@Autowired
	public void setConfigDataService(ConfigDataService configDataService) {
		this.configDataService = configDataService;
	}

	@Override
	public void updateConfigInfo(@Param("configKey")String configKey, 
			@Param("configValue")String configValue, @Param("expiresIn")int expiresIn) {
		configDataService.updateConfigInfo(configKey, configValue, expiresIn);
	}

	@Override
	public String getConfigValue(String configKey) {
		String configValue = configDataService.getConfigValue(configKey);
		return configValue;
	}



}
