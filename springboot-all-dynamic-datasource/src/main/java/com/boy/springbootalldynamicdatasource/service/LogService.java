package com.boy.springbootalldynamicdatasource.service;



import com.boy.springbootalldynamicdatasource.bean.Log;
import com.boy.springbootalldynamicdatasource.bean.LogPool;
import com.boy.springbootalldynamicdatasource.bean.LogStsQry;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 日志信息
 * 
 * @author LEICHAO
 * @date 2018年6月11日下午3:13:11
 */
public interface LogService {
	/**
	 * 查找日志信息
	 * 
	 * @param log
	 * @return
	 */
	public Log get(Log log);

	/**
	 * 查找日志信息
	 * 
	 * @param log
	 * @param date
     * @param url
* @param params
* @param module
* @param operate
* @param status
	 * @return
	 */
	public List<Log> findList(String startDate, String endDate, String userCode, String url, String params, String module, String operate, String type);

	/**
	 * 添加日志信息
	 * 
	 * @param log
	 */
	public void insert(Log log);

	/**
	 * 修改日志信息
	 * 
	 * @param log
	 */
	public void update(Log log);

	/**
	 * 删除日志信息
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 按功能统计
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<LogStsQry> statisticsByMenu(String startDate, String endDate);

	/**
	 * 按用户统计
	 * 
	 * @param
	 * @param endDate
	 * @return
	 */
	public List<LogStsQry> statisticsByUser(String startDate, String endDate);

    List<LogPool> findListPool(String startDate, String endDate, String userCode);



    void saveLog(HttpServletRequest request, String ex
            , String param, String module, String operate);
}
