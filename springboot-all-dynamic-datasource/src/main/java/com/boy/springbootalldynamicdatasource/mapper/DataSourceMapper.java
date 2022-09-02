package com.boy.springbootalldynamicdatasource.mapper;

import com.boy.springbootalldynamicdatasource.bean.DataSource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author luoheng
 * @date 创建时间：2017年8月17日下午3:36:14
 * @version 1.0 
 */
@Repository
public interface DataSourceMapper extends BaseMapper<DataSource> {

    String getIdByCode(String dataSource);

    List<DataSource> findListByDsrcCode(@Param("dsrcCode") String dsrcCode);
}
