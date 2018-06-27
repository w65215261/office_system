package com.hndl.mobileplatform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hndl.mobileplatform.dao.DlnoticepersonMapper;
import com.hndl.mobileplatform.model.Dlnoticeperson;
import com.hndl.mobileplatform.model.DlnoticepersonExample;
import com.hndl.mobileplatform.service.DlNoticPersonService;
import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.common.BaseUtil;
import com.pmcc.soft.core.common.BaseVO;
import com.pmcc.soft.core.common.HttpClientPost;
import com.pmcc.soft.core.utils.SystemParamsUtil;
@Service
public class DlNoticPersonServiceImpl implements DlNoticPersonService{
	@Autowired
	DlnoticepersonMapper dlNoticePersonMapper;
	@Resource
    private PublicExcuteSqlService publicExcuteSqlService;
	@Override
	public List<Dlnoticeperson> selectByExample(DlnoticepersonExample example) {
		// TODO Auto-generated method stub
		return dlNoticePersonMapper.selectByExample(example);
	}
	@Override
	public List<Dlnoticeperson> selectByExample(DlnoticepersonExample example,BaseVO vo) {
		// TODO Auto-generated method stub
		vo.setOrderField("accepttime");
		example.setVo(vo);
		return dlNoticePersonMapper.selectByExample(example);
	}

	@Override
	public int insert(Dlnoticeperson record) {
		
		return dlNoticePersonMapper.insert(record);
	}

	@Override
	public int deleteByPrimaryKey(String fileid) {
		return dlNoticePersonMapper.deleteByPrimaryKey(fileid);
	}
	@Override
	public String sendWeiXin(String infoid,String personid,String uri,String token,String appId) {
		// TODO Auto-generated method stub
//		String personSql ="select * from PERSON_INFO where OID='"+personid+"'";
		String personSql ="select * from PERSON_INFO where USER_ENAME='"+personid+"'";

		String infoSql = "select * from dlNoticeManage where id='"+infoid+"'";
		List<Map> personList = publicExcuteSqlService.executeSql(personSql);
		String resultFlag ="";
		if(personList.size()>0){
			if(!"".equals(personList.get(0).get("WECHAT_ID")) && personList.get(0).get("WECHAT_ID")!=null){
				List<Map> infoList = publicExcuteSqlService.executeSql(infoSql);

				Map<String, Object> jsonMap = new HashMap<String, Object>();
				// openid
				jsonMap.put("touser", personList.get(0).get("WECHAT_ID"));
				// 点击详情跳转的url
				jsonMap.put("url", SystemParamsUtil.getSysConfig().get("webAddress").toString()+"mobileManager/selectNoticeByid.do?android=true&noticeid="+infoid+"&personid="+personList.get(0).get("OID"));
				// 标题
				jsonMap.put("title", infoList.get(0).get("title"));
				// 发布时间
				jsonMap.put("time", BaseUtil.getDataFromLong(Long.valueOf(infoList.get(0).get("createtime").toString())));
				// 发布者
				jsonMap.put("aouther", infoList.get(0).get("createid"));
				// 内容摘要
				jsonMap.put(
						"remark",
						infoList.get(0).get("content").toString().length() > 10 ? infoList.get(0)
								.get("content").toString().substring(0, 9)
								+ "......" : infoList.get(0).get("content").toString());
				JSONObject jsonObject = JSONObject.fromObject(jsonMap);

				String result = HttpClientPost.post(jsonObject.toString(),uri,token,appId);
				JSONObject jsonResult = JSONObject.fromObject(result);
				resultFlag = jsonResult.get("errcode").toString();
			}
		}
	
		if(!resultFlag.equals("请求成功")){
			return "发送失败";
		}else{
			return "发送成功";
		}
	}
	@Override
	public int countByExample(DlnoticepersonExample example) {
	
		return dlNoticePersonMapper.countByExample(example);
	}
	@Override
	public int updateByPrimaryKey(Dlnoticeperson record) {
	
		return dlNoticePersonMapper.updateByPrimaryKey(record);
	}
}
