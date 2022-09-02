package com.boy.springbootalldynamicdatasource.mapper;

import com.boy.springbootalldynamicdatasource.bean.Log;
import com.boy.springbootalldynamicdatasource.bean.LogPool;
import com.boy.springbootalldynamicdatasource.bean.LogStsQry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 日志信息
 * @author LEICHAO
 * @date 2018年6月11日下午3:08:30
 */
public interface LogMapper {
	/**
	 * 查找日志信息
	 * 
	 * @param
	 * @return
	 */
	public Log get(Log log);

	/**
	 * 查找日志信息
	 * 
	 * @param
	 * @param
     * @param
     * @param module
     * @param operate
     * @param
     * @return
	 */
	public List<Log> findList(@Param("startDate") String startDate,
                              @Param("endDate") String endDate,
                              @Param("userCode") String userCode,
                              @Param("requestUrl") String requestUrl,
                              @Param("params") String params,
                              @Param("module") String module,
                              @Param("operate") String operate, @Param("type")String type);

	/**
	 * 添加日志信息
	 *
	 * @param
	 */
	public void insert(Log log);

	/**
	 * 更新日志信息
	 *
	 * @param
	 */
	public void update(Log log);

	/**
	 * 根据id删除
	 *
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 按功能统计
	 * @param
	 * @param endDate
	 * @return
	 */
	public List<LogStsQry> statisticsByMenu(String startDate, String endDate);

	/**
	 * 按用户统计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<LogStsQry> statisticsByUser(String startDate, String endDate);

    List<LogPool> findListPool(@Param("startDate")String startDate,
							   @Param("endDate")String endDate,
							   @Param("userCode")String userCode);
}
