/**
 * 
 */
package com.jmh.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.jmh.model.AccessToken;
import com.jmh.model.WeChatUserInfo;

/**
 * @author chen, weiwei
 * @date 08/23/2019
 *
 */
public final class WeChatUtils {
	
	private static Log log = LogFactory.getLog(WeChatUtils.class);
	
	private static final String token = "jmh200312";
	private static final String APPID = "wxa18ab49a349b53d0";
	private static final String APPSECRET = "96c467051628d0211aa64de272fabdc4";
	private static final String ACCESS_OAUTH_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?"
			+ "appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?"
			+ "grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final String ACCESS_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?"
			+ "access_token=ACCESSTOKEN&openid=OPNDID";
	

	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = new String[] { token, timestamp, nonce };
		// 排序
		Arrays.sort(arr);

		// 生成字符串
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}

		// sha1加密
		String temp = getSha1(content.toString());

		return temp.equals(signature);
	}

	/**
	 * Sha1加密方法
	 * 
	 * @param str
	 * @return
	 */
	public static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 编写Get请求的方法。但没有参数传递的时候，可以使用Get请求
	 *
	 * @param url
	 *            需要请求的URL
	 * @return 将请求URL后返回的数据，转为JSON格式，并return
	 */
	public static JSONObject doGetStr(String url) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();// 获取DefaultHttpClient请求
		HttpGet httpGet = new HttpGet(url);// HttpGet将使用Get方式发送请求URL
		JSONObject jsonObject = null;
		HttpResponse response = client.execute(httpGet);// 使用HttpResponse接收client执行httpGet的结果
		HttpEntity entity = response.getEntity();// 从response中获取结果，类型为HttpEntity
		log.info("entity = " + entity);
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");// HttpEntity转为字符串类型
			log.info("result = " + result);
			jsonObject = JSONObject.parseObject(result);// 字符串类型转为JSON类型
			log.info("jsonObject = " + jsonObject.toJSONString());
		}
		return jsonObject;
	}

	/**
	 * 编写Post请求的方法。当我们需要参数传递的时候，可以使用Post请求
	 *
	 * @param url
	 *            需要请求的URL
	 * @param outStr 
	 *            需要传递的参数
	 * @return 将请求URL后返回的数据，转为JSON格式，并return
	 */
	public static JSONObject doPostStr(String url, String outStr) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();// 获取DefaultHttpClient请求
		HttpPost httpost = new HttpPost(url);// HttpPost将使用Get方式发送请求URL
		JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr, "UTF-8"));// 使用setEntity方法，将我们传进来的参数放入请求中
		HttpResponse response = client.execute(httpost);// 使用HttpResponse接收client执行httpost的结果
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");// HttpEntity转为字符串类型
		jsonObject = JSONObject.parseObject(result);// 字符串类型转为JSON类型
		return jsonObject;
	}

	/**
	 * 获取AccessToken
	 * 
	 * @return 返回拿到的access_token及有效期
	 */
	public static AccessToken getAccessToken() throws ClientProtocolException, IOException {
		AccessToken token = new AccessToken();
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);// 将URL中的两个参数替换掉
		JSONObject jsonObject = doGetStr(url);// 使用刚刚写的doGet方法接收结果
		if (jsonObject != null) { // 如果返回不为空，将返回结果封装进AccessToken实体类
			token.setToken(jsonObject.getString("access_token"));// 取出access_token
			token.setExpiresIn(jsonObject.getInteger("expires_in"));// 取出access_token的有效期
		}
		return token;
	}
	
    // 获取关注用户
    public static WeChatUserInfo getWeChatUserInfo(String token, String openId) {
    	WeChatUserInfo info = new WeChatUserInfo();
        try {
        	String url = ACCESS_USER_INFO_URL.replace("ACCESSTOKEN", token).replace("OPNDID", openId);// 将URL中的两个参数替换掉
    		JSONObject jsonObject = doGetStr(url);// 使用刚刚写的doGet方法接收结果
            //请求接口地址
    		if (jsonObject != null) { // 如果返回不为空，将返回结果封装进AccessToken实体类
    			info.setCity(jsonObject.getString("city"));// 取出city
    			info.setCountry(jsonObject.getString("country"));// 取出country
    			info.setHeadimgurl(jsonObject.getString("headimgurl"));// 取出headimgurl
    			info.setLanguage(jsonObject.getString("language"));// 取出language
    			info.setNickname(jsonObject.getString("nickname"));// 取出nickname
    			info.setOpenid(jsonObject.getString("openid"));// 取出openid
    			info.setProvince(jsonObject.getString("province"));// 取出province
    			info.setSex(jsonObject.getInteger("sex"));// 取出sex
    			info.setSubscribe_time(jsonObject.getSqlDate("subscribe_time"));// 取出subscribe_time
    		}
    		log.info("url = " + url);
    		log.info("JsonInfo = " + jsonObject.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return info;
    }
    
    public static String oauth2GetOpenid(String code) {
        //拼接用户授权接口信息
        String url = ACCESS_OAUTH_URL.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);
        log.info("url = " + url);
        //存储获取到的授权字段信息
        String openid = null;
        try {
            JSONObject OpenidJSONO = doGetStr(url);
            //OpenidJSONO可以得到的内容：access_token expires_in  refresh_token openid scope
            openid = String.valueOf(OpenidJSONO.get("openid"));
            log.info("openid = " + openid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return openid;
    }
}
