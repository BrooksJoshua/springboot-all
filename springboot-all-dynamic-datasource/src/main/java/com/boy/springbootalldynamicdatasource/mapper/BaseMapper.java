package com.boy.springbootalldynamicdatasource.mapper;

import java.util.List;

/**
 * @autor:JiaLing Li
 * @param <T>
 */
public interface BaseMapper<T> {

	/**
	   * 获取单条数据.
	   * 
	   * @param id 主键ID
	   * @return 实体对象
	   */
	  public T get(String id);

	  /**
	   * 获取单条数据.
	   * 
	   * @param entity 实体对象
	   * @return 返回获取的实体对象
	   */
	  @Deprecated
	  public T get(T entity);

	  /**
	   * 获取单条数据.
	   * 
	   * @param entity 实体对象
	   * @return 检索结果实体对象
	   */
	  public T findOne(T entity);

	  /**
	   * 查询数据列表，如果需要分页，请设置分页对象.
	   * 
	   * @param entity 实体对象
	   * @return 实体对象列表
	   */
	  public List<T> findList(T entity);

	  /**
	   * 查询所有数据列表.
	   * 
	   * @param entity 实体对象
	   * @return 实体对象列表
	   */
	  public List<T> findAllList(T entity);

	  /**
	   * 查询所有数据列表.
	   * 
	   * @return 实体对象列表
	   */
//	  @Deprecated
	  public List<T> findAllList();

	  /**
	   * 插入数据.
	   * 
	   * @param entity 实体对象
	   * @return 插入记录数
	   */
	  public int insert(T entity);

	  /**
	   * 更新数据.
	   * 
	   * @param entity 实体对象
	   * @return 更新记录数
	   */
	  public int update(T entity);

	  /**
	   * 删除数据（一般为逻辑删除，更新del_flag字段为1）.
	   * 
	   * @param id 主键ID
	   * @see public int delete(T entity)
	   * @return 删除记录数
	   */
	  @Deprecated
	  public int delete(String id);

	  /**
	   * 删除数据（一般为逻辑删除，更新del_flag字段为1）.
	   * 
	   * @param entity 实体对象
	   * @return 删除记录数
	   */
	  public int delete(T entity);
}
