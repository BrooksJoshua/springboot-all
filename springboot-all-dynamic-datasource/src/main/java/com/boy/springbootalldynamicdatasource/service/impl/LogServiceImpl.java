package com.boy.springbootalldynamicdatasource.service.impl;

import com.alibaba.fastjson.JSON;
import com.boy.springbootalldynamicdatasource.bean.Log;
import com.boy.springbootalldynamicdatasource.bean.LogPool;
import com.boy.springbootalldynamicdatasource.bean.LogStsQry;
import com.boy.springbootalldynamicdatasource.bean.UserInfo;
import com.boy.springbootalldynamicdatasource.config.DynamicDataSourceContextHolder;
import com.boy.springbootalldynamicdatasource.mapper.LogMapper;
import com.boy.springbootalldynamicdatasource.service.LogService;
import com.boy.springbootalldynamicdatasource.util.StringUtils;
import com.boy.springbootalldynamicdatasource.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日志信息
 * 
 * @author LEICHAO
 * @date 2018年6月11日下午3:13:22
 */
@Service("logService")
public class LogServiceImpl implements LogService {
	@Autowired
	private LogMapper logMapper;

	@Override
	public Log get(Log log) {
		return logMapper.get(log);
	}

	@Override
	public List<Log> findList( String startDate, String endDate, String userCode, String url, String params, String module, String operate,String type) {
		return logMapper.findList(startDate, endDate,userCode,url,params, module,  operate,type);
	}

	@Override
	public void insert(Log log) {
		logMapper.insert(log);
	}

	@Override
	public void update(Log log) {
		logMapper.update(log);
	}

	@Override
	public void delete(String id) {
		logMapper.delete(id);
	}

	@Override
	public List<LogStsQry> statisticsByMenu(String startDate, String endDate) {
		return logMapper.statisticsByMenu(startDate, endDate);
	}

	@Override
	public List<LogStsQry> statisticsByUser(String startDate, String endDate) {
		return logMapper.statisticsByUser(startDate, endDate);
	}

	@Override
	public List<LogPool> findListPool(String startDate, String endDate, String userCode) {
		return logMapper.findListPool(startDate, endDate, userCode) ;
	}


	@Override
	public void saveLog(HttpServletRequest request, String ex
			, String param, String module, String operate) {
		DynamicDataSourceContextHolder.setDataSourceType("defaut");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userinfo");

		// 保存日志信息
		Log log = new Log();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		if(Utils.isEmptyStr(userInfo)){
			log.setCreateBy("system");
		}else{
			log.setCreateBy(userInfo.getName());
		}
		log.setCreateDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		log.setCreateTime(new Date());
		log.setRemoteAddr(StringUtils.getRemoteAddr(request));
		log.setUserAgent(request.getHeader("user-agent"));
		log.setRequestUrl(request.getServletPath());
		log.setMethod(request.getMethod());
		log.setOperate(operate);
		log.setModule(module);
		if (!Utils.isEmptyStr(ex)) {
			log.setException(ex);
			log.setType(0);
		}else{
			log.setType(1);
		}
		Map<String, String> parmMap = new HashMap<String, String>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			parmMap.put(paraName, request.getParameter(paraName));
		}

		if (!parmMap.isEmpty() && Utils.isEmptyStr(param)) {
			log.setParams(JSON.toJSONString(parmMap));
		}else{
			log.setParams(param);
		}
		insert(log);
	}
}
