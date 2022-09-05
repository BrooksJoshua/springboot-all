package com.boy.springbootalldynamicdatasource.service.impl;

import com.boy.springbootalldynamicdatasource.bean.DataSource;
import com.boy.springbootalldynamicdatasource.config.JdbcTemplateManager;
import com.boy.springbootalldynamicdatasource.mapper.DataSourceMapper;
import com.boy.springbootalldynamicdatasource.service.DataSourceService;
import com.boy.springbootalldynamicdatasource.util.DBHelper;
import com.boy.springbootalldynamicdatasource.util.DataSourceUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;

@Service("dataSourceService")
public class DataSourceServiceImpl extends BaseServiceImpl<DataSourceMapper, DataSource> implements DataSourceService {


	private static Logger logger = LoggerFactory.getLogger(DataSourceServiceImpl.class);

	/** 延迟初始化 */
	private static final long DELAY_START = 5 * 1000L;

	@Autowired
	private DataSourceService dataSourceService;

	@PostConstruct
	public void init() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				loadAllDataSource();
			}
		}, DELAY_START);
	}

	@Override
	public int updateBySys(DataSource entity) {
		// TODO Auto-generated method stub
		entity.setUpdateDate(new Date());
		entity.setUpdateBy("0");
		return dao.update(entity);
	}

	@Override
	public String getIdByCode(String code) {
		return dao.getIdByCode(code);
	}



	@Override
	public String saveDataSource(DataSource entity) {
		entity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		entity.setDelFlag("0");
		entity.setCreateDate(new Date());
		entity.setUpdateDate(new Date());
		dao.insert(entity);
		return entity.getId();
	}

	@Override
	public List<DataSource> findListByDsrcCode(String dsrcCode) {
		return dao.findListByDsrcCode(dsrcCode);
	}


	public Map<String,Object> generateDruidDataSourceParameter(DataSource dataSource){
		if (dataSource == null || dataSource.getId() == null || dataSource.getDbType() == null || StringUtils.isBlank(dataSource.getUrl())
				|| StringUtils.isBlank(dataSource.getUsername()) || StringUtils.isBlank(dataSource.getPassword())) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dbType", dataSource.getDbType().getCode());
		map.put("driverClassName", dataSource.getDbType().getDriverClass());
		map.put("url", dataSource.getUrl());
		map.put("username", dataSource.getUsername());
		map.put("validationQuery","select 1");
		map.put("testOnBorrow",true);
		map.put("testOnReturn",false);
		map.put("testWhileIdle",true);
		map.put("timeBetweenEvictionRunsMillis",60000);
		map.put("minEvictableIdleTimeMillis",150000);
		map.put("connectionErrorRetryAttempts",3);//连接出错后再尝试连接三次
		map.put("breakAfterAcquireFailure",true);//数据库服务宕机自动重连机制
		map.put("removeAbandoned",true);//是否自动回收超时连接
		map.put("removeAbandonedTimeout",1800);//超时时间(以秒数为单位)
		map.put("transactionQueryTimeout",6000);//事务超时时间
		if("presto"!=dataSource.getDbType().getCode()) {
			map.put("password", dataSource.getPassword());//解密
		}
		if (dataSource.getInitSize() != null && dataSource.getInitSize() > 0) {map.put("initialSize", dataSource.getInitSize());}
		if (dataSource.getMinIdle() != null && dataSource.getMinIdle() > 0) {
			map.put("minIdle", dataSource.getMinIdle());
		}
		if (dataSource.getMaxActive() != null && dataSource.getMaxActive() > 0) {
			map.put("maxActive", dataSource.getMaxActive());
		}
		if (dataSource.getMaxWait() != null && dataSource.getMaxWait() > 0) {
			map.put("maxWait", dataSource.getMaxWait());
		}
		if (dataSource.getTimeBetweenEvictionRunMillis() != null && dataSource.getTimeBetweenEvictionRunMillis() > 0) {
			map.put("timeBetweenEvictionRunsMillis", dataSource.getTimeBetweenEvictionRunMillis());
		}
		if (dataSource.getMinEvictableIdleTimeMillis() != null && dataSource.getMinEvictableIdleTimeMillis() > 0) {
			map.put("minEvictableIdleTimeMillis", dataSource.getMinEvictableIdleTimeMillis());
		}
		if (dataSource.getQueryTimeout() != null && dataSource.getQueryTimeout() >0){
			map.put("queryTimeout",dataSource.getQueryTimeout());
		}
		/*if (StringUtils.isNotBlank(dataSource.getValidationQuery())) {
			map.put("testWhileIdle", true);
			map.put("validationQuery", dataSource.getValidationQuery());
		} else {
			map.put("testWhileIdle", false);
		}*/
		return map;
	}



	/**
	 * 项目启动加载全部数据源
	 */
	private void loadAllDataSource() {

		DataSource db = new DataSource();
		List<DataSource> list = dataSourceService.findList(db);
		for (DataSource dataSource : list) {
			try {
				addDataSource(dataSource);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 *  添加数据源
	 *  1. 首先校验：a. 空值 b. 重复性 c. 连通性
	 *  2. 生成druid连接池参数map
	 *  3. 往容器中添加当前datasource
	 *  4. 往JdbcTemplateManager中添加: JdbcTemplateManager.put(dataSource.getId(), dataSource.getBeanName()); 下一次验证重复性的时候会用到。
	 * @param dataSource 后台查到的datsSourceList里遍历的其中一个
	 * @throws Exception
	 */
	public synchronized void addDataSource(DataSource dataSource) throws Exception {
		if (dataSource == null){
			return;
		}
		if (JdbcTemplateManager.getJdbcTemplate(dataSource.getId()) != null) {
			return;
		}
		if (!DBHelper.checkConnectionDB(dataSource.getDbType().getDriverClass(), dataSource.getUrl(),
				dataSource.getUsername(), dataSource.getPassword())) {//解密
			throw new Exception("connect db failed!");
		}

		Map<String, Object> parameterMap = generateDruidDataSourceParameter(dataSource);
		DataSourceUtil.addDataSource(dataSource.getBeanName(),parameterMap, dataSource.getQueryTimeout());
		JdbcTemplateManager.put(dataSource.getId(), dataSource.getBeanName());
	}

	/**
	 * 在Spring容器中删除数据源
	 */
	@Override
	public void removeDataSource(String id, String beanName) {
		JdbcTemplateManager.remove(id);
		DataSourceUtil.removeDataSource(beanName);
	}

	/**
	 * 同步数据库中的数据源到Spring容器
	 */
	@Override
	public void sync() {
		DataSource db = new DataSource();
		List<DataSource> properties = dataSourceService.findList(db);
		if (CollectionUtils.isEmpty(properties)) {
			return;
		}

		// 同步数据库中新增的数据源到Spring容器
		Set<String> dbIds = new HashSet<String>(properties.size());
		for (DataSource property : properties) {
			dbIds.add(property.getId());
			if (JdbcTemplateManager.getJdbcTemplate(property.getId()) != null) {
				// 跳过容器中已存在的记录
				continue;
			}
			try {
				addDataSource(property);
			} catch (Exception e) {
				logger.error("connection database error! DataSource=" + property, e);
			}
		}
		for (Map.Entry<String, String> e : JdbcTemplateManager.getAllBean()) {
			if (!dbIds.contains(e.getKey())) {
				removeDataSource(e.getKey(), e.getValue());
			}
		}

	}


}
