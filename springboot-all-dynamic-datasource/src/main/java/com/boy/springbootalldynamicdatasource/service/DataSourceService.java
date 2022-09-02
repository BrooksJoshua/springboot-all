package com.boy.springbootalldynamicdatasource.service;

import com.boy.springbootalldynamicdatasource.bean.DataSource;


import java.util.List;

public interface DataSourceService extends BaseService<DataSource> {

	  /**
	   * 更新数据.
	   * 
	   * @param entity 实体对象
	   * @return 更新记录数
	   */
	  public int updateBySys(DataSource entity);

	  public String getIdByCode(String code);

	  String saveDataSource(DataSource entity);

    List<DataSource> findListByDsrcCode(String dsrcCode);

    void addDataSource(DataSource dataSource) throws Exception;

	void removeDataSource(String id, String beanName);

    void sync();
}
