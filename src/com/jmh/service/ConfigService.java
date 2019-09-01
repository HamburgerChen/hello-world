/**
 * 
 */
package com.jmh.service;

/**
 * @author chen, weiwei
 * @date 08/28/2018
 *
 */
public interface ConfigService {
	void updateConfigInfo(String configKey, String configValue, int expiresIn);

	String getConfigValue(String configKey);
}
