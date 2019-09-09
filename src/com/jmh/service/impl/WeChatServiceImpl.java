/**
 * 
 */
package com.jmh.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.jmh.dao.WeChatDataService;
import com.jmh.model.CheckCodeResult;
import com.jmh.model.JobProvidedInfo;
import com.jmh.model.JobWantedInfo;
import com.jmh.model.SMSAliInfo;
import com.jmh.model.TextMessage;
import com.jmh.service.WeChatService;
import com.jmh.utils.Constants;
import com.jmh.utils.SMSAliUtils;
import com.jmh.utils.WebUtils;

/**
 * @author chen, weiwei
 * @date 08/13/2019
 *
 */
@Service("weChatService")
public class WeChatServiceImpl implements WeChatService {

	private static Log log = LogFactory.getLog(WeChatServiceImpl.class);

	private SMSAliInfo smsAliInfo;

	@Autowired
	public void setInfo(SMSAliInfo smsAliInfo) {
		this.smsAliInfo = smsAliInfo;
	}

	private WeChatDataService weChatDataService;

	@Autowired
	public void setWeChatDataService(WeChatDataService weChatDataService) {
		this.weChatDataService = weChatDataService;
	}

	@Override
	public CheckCodeResult sendSMS(String telephone, String checkCode) throws ServerException, ClientException {
		CheckCodeResult result = new CheckCodeResult();
		smsAliInfo.setTelephone(telephone);
		smsAliInfo.setTemplateParam(checkCode);
		SendSmsResponse smsAck = SMSAliUtils.sendSMS(smsAliInfo);
		result.setTelephone(telephone);
		if (smsAck == null) {
			log.error("smsAck error, no sms come back.");
			result.setStatusCode(Constants.FAILUE);
			return result;
		}

		if (smsAck.getCode().equals(Constants.SENT_SUCCEED) || smsAck.getCode().isEmpty()) {
			result.setCheckCode(checkCode);
			result.setStatusCode(Constants.SUCCESS);
		} else {
			result.setStatusCode(Constants.FAILUE);
			result.setMessage(smsAck.getMessage());
		}
		return result;
	}

	@Override
	public List<JobProvidedInfo> getAllJobProvidedInfo(
			@Param("startPage")int startPage, @Param("pageSize")int pageSize) {
		List<JobProvidedInfo> jobProvidedInfos = weChatDataService.getAllJobProvidedInfo(startPage, pageSize);
		return jobProvidedInfos;
	}

	@Override
	public int getAllJobProvidedCount() {
		int result = weChatDataService.getJobProvidedCount();
		return result;
	}

	@Override
	public String newJobProvidedInfo(JobProvidedInfo jobInfo) {
		String jobId = weChatDataService.newJobProvidedId();
		jobInfo.setJobId(jobId);
		if(weChatDataService.newJobProvidedInfo(jobInfo) == 1){
			return jobId;
		}
		return null;
	}

	@Override
	public int updateJobProvidedInfo(JobProvidedInfo jobInfo) {
		int updateCode = weChatDataService.updateJobProvidedInfo(jobInfo);
		return updateCode;
	}

	@Override
	public int deleteJobProvidedInfo(String jobId) {
		int deleteCode = weChatDataService.deleteJobProvidedInfo(jobId);
		return deleteCode;
	}

	@Override
	public String getPhone(String openId) {
		String phone = weChatDataService.getPhone(openId);
		return phone;
	}

	@Override
	public JobProvidedInfo getJobProvidedInfo(String jobId) {
		JobProvidedInfo result = weChatDataService.getJobProvidedInfo(jobId);
		return result;
	}

	@Override
	public List<JobProvidedInfo> getAllJobPvdInfoByCompId(
			@Param("companyId")String companyId, @Param("startPage")int startPage, @Param("pageSize")int pageSize) {
		List<JobProvidedInfo> jobProvidedInfos = weChatDataService.getAllJobPvdInfoByCompId(companyId, startPage, pageSize);
		return jobProvidedInfos;
	}

	@Override
	public int getJobProvidedCompCount(String companyId) {
		int result = weChatDataService.getJobProvidedCompCount(companyId);
		return result;
	}

	@Override
	public List<JobWantedInfo> getAllJobWantedInfo(@Param("startPage")int startPage, @Param("pageSize")int pageSize) {
		List<JobWantedInfo> jobWantedInfos = weChatDataService.getAllJobWantedInfo(startPage, pageSize);
		return jobWantedInfos;
	}

	@Override
	public int getAllJobWantedCount() {
		int result = weChatDataService.getAllJobWantedCount();
		return result;
	}

	@Override
	public JobWantedInfo getJobWantedInfo(String wantedId) {
		JobWantedInfo result = weChatDataService.getJobWantedInfo(wantedId);
		return result;
	}

	@Override
	public String newJobWantedInfo(JobWantedInfo wantedInfo) {
		String wantedId = weChatDataService.newJobWantedId();
		wantedInfo.setWantedId(wantedId);
		if(weChatDataService.newJobWantedInfo(wantedInfo) == 1){
			return wantedId;
		}
		return null;
	}

	@Override
	public String processRequest(HttpServletRequest request) throws Exception {
		log.info("processing weChat Click event response...");
		String xml = null;
		Map<String, String> requestMap = WebUtils.parseXml(request);
		log.info("weChat request map as: " + requestMap);
		// 消息类型
		String event = requestMap.get("Event");
		String eventKey = requestMap.get("EventKey");
		String fromUserName = requestMap.get("FromUserName");
		String toUserName = requestMap.get("ToUserName");
		if("CLICK".equals(event)){
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType("text");
			textMessage.setFuncFlag("0");
			if("jmhContact".equals(eventKey)){
				textMessage.setContent("400-8277-616");
			}else if("jmhActivities".equals(eventKey)){
				textMessage.setContent(JMH_ACTIVITIES);
			}else if("jmhBossCourse".equals(eventKey)){
				textMessage.setContent(JMH_BOSS_COURSE);
			}else if("jmhVIP".equals(eventKey)){
				textMessage.setContent(JMH_VIP);
			}
			xml = WebUtils.messageToXml(textMessage);
		}
		
		return xml;
	}
	
	private static String JMH_ACTIVITIES = "  巡回公益慰演是军才联盟公益行的内容之一，是一个大型公益双拥活动。其目的是通过退伍季公益慰演、巡回公益慰演的形式，向社会传播正能量，号召全民关注退役军人就业创业，"
			+ "关心退役军人生活。激发全民爱党、爱国、爱军队的热情。从而凝聚社会各界力量帮扶解决退役军人在就业创业道路上所遇到的知识结构更新、职业技能提升、创新意识培养、社会角色转换等现实问题。同时，通过活动让拥军爱民的浪潮席卷中华大地，"
			+ "并逐渐形成全民关爱退役军人，退役军人助力社会经济发展的良好社会风气。\n  本次活动由军才学院联盟理事会发起，军梦汇-创新型退役军人服务平台为活动承办单位。\n  活动为全国性、联合性、巡回型、不定期型；有需求的部队、事务局、爱心企业都可以申请，网上提交申请，平台审核通过后即可实施.";
	private static String JMH_BOSS_COURSE = "孵化百强军企先锋，实现千万军人梦想！特训营将优选1000位优质企业！帮助100位企业上市！为此军才联盟邀请全国知名专家教授为企业家问诊把脉，帮助企业再创辉煌！凡是加入军才联盟的成员单位，都可免费或优惠参加企业家精英-特训营！得到国内顶尖大咖的指导帮扶！"
			+"\n特训营课程：\n  1、	路长全--营销咨询式特训营：2000万到5亿五部方法\n  2、	石泽杰--互联网+商业模式创新\n  3、	宋俊生--股权顶层设计与股权激励\n  4、	孙正伟--经营转型与盈利再造\n  5、	容易--构建企业铁军人才\n  6、	李鹏—企业上市过程中财务与税收管理"
			+"\n  7、	洪生—战略性人力资源管理\n  8、	马思宇—心理学在管理中的应用\n  9、	王怀芳—企业融资战略与价值创造\n  10、	钱锦国—国学智慧卓越领导之道";
	private static String JMH_VIP = "军才联盟成员专属权益\n一、权益（价值3万元专属礼包）\n1．企业专属账号密码，独立操作发布信息，提供退役军人招生招聘专属通道。\n2、颁发军才联盟成员牌匾证书，推荐参加双拥个人、单位评选。3、免费提供军营专场招聘会入场券10张（1000元/场）,免费参加军才联盟公益活动，并提供爱心企业专属展示宣传机会。"
			+"\n4、免费提供拥军企业家赞助的价值1万元礼品。\n5、免费或优惠参加联盟组织的人力资源、行政管理、HR知识讲座培训课，价值1980元/次（免费3次，其余的半价）。\n6、免费或优惠参加联盟组织的顶级营销专家（路长全）、等专家教授的企业家总裁班培训班，价值3980元/次，（免费1次，其余的半价）。"
			+"\n7、免费提供企业招生招聘信息发布30条（500元/条）。\n8、免费提供企业产品在联盟商城上的销售展示通道。\n9、优先提供企业家《特别助理》专属人才定制培训服务。\n10、上述服务项目按价格可以服务费中抵扣。\n二、义务:\n1．积极配合联盟对退役军人人力资源状况及企业军人用工需求状况的调研"
			+"\n2．积极参加联盟举办的相关活动，并向联盟提出意见和建议。\n3．积极缴纳联盟服务费。\n4、服务费：成员企业为中大型实体生产企业，省级城市1000家，地市级500家，服务费3万元/3年；前500名1万元，满额后恢复原价，协议签订后一次性收取，统一打入企业账户，严禁收取现金或工作人员代收。";
}
