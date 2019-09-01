package com.jmh.dao;

import org.apache.ibatis.annotations.Param;

public interface ConfigDataService {

	void updateConfigInfo(@Param("configKey")String configKey, @Param("configValue")String configValue, @Param("expiresIn")int expiresIn);

	String getConfigValue(String configKey);

}
