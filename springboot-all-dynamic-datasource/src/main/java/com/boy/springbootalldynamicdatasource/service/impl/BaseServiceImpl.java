package com.boy.springbootalldynamicdatasource.service.impl;

import com.boy.springbootalldynamicdatasource.bean.BaseBean;
import com.boy.springbootalldynamicdatasource.bean.User;
import com.boy.springbootalldynamicdatasource.bean.UserInfo;
import com.boy.springbootalldynamicdatasource.mapper.BaseMapper;
import com.boy.springbootalldynamicdatasource.mapper.UserMapper;
import com.boy.springbootalldynamicdatasource.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 
 * @autor:JiaLing Li
 */
public abstract class BaseServiceImpl<D extends BaseMapper<T>, T extends BaseBean<T>> implements BaseService<T> {

	@Autowired
	protected D dao;

	@Autowired
	private UserMapper userMapper;

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public T get(String id) {
		return dao.get(id);
	}

	/**
	 * 获取单条数据 建议使用get(T entity)方法代替
	 * 
	 * @param entity
	 * @return
	 */
	@Deprecated
	@Override
	public T get(T entity) {
		return dao.get(entity);
	}

	@Override
	public T findOne(T entity) {
		return dao.findOne(entity);
	}

	/**
	 * 查询列表数据
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}

	@Override
	public List<T> findAllList(T entity) {
		return dao.findAllList(entity);
	}

	/**
	 * 查询列表所有数据
	 * 
	 * @return
	 */
	@Override
	public List<T> findAllList() {
		return dao.findAllList();
	}

	@Override
	public int save(T entity) {
		entity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		entity.setDelFlag("0");
		entity.setCreateDate(new Date());
		entity.setUpdateDate(new Date());

		// UserInfo userInfo = (UserInfo) SecurityContextHdeleteByUserIdolder.getContext()
		// .getAuthentication()
		// .getPrincipal();
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userinfo");
		User loginUser = userMapper.getByUserCode(userInfo.getName());

		entity.setCreateBy(loginUser.getId());
		entity.setUpdateBy(loginUser.getId());
		return dao.insert(entity);
	}

	@Override
	public int saveInfo(T entity) {
		entity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		entity.setDelFlag("0");
		entity.setCreateDate(new Date());
		entity.setUpdateDate(new Date());
		return dao.insert(entity);
	}

	@Override
	public int update(T entity) {
		entity.setUpdateDate(new Date());

		// UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext()
		// .getAuthentication()
		// .getPrincipal();
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userinfo");
		User loginUser = userMapper.getByUserCode(userInfo.getName());
		entity.setUpdateBy(loginUser.getId());
		return dao.update(entity);
	}

	@Override
	public int delete(String id) {
		return dao.delete(id);
	}

	/**
	 * 删除数据
	 * 
	 * @param entity
	 */
	@Override
	public int delete(T entity) {
		return dao.delete(entity);
	}

}
