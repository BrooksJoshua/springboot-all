package com.boy.springbootalldynamicdatasource.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * aop切面，用于动态修改数据源
 */
@Aspect
@Order(-1) // 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

	private final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

	@Before("execution(* com.boy.springbootalldynamicdatasource.mapper.*.*(..))")
	public void before(JoinPoint point) {
		/*Object target = point.getTarget();
		String method = point.getSignature().getName();

		Class<?>[] classz = target.getClass().getInterfaces();

		Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
		try {
			Method m = classz[0].getMethod(method, parameterTypes);
			if (m != null && m.isAnnotationPresent(TargetDataSource.class)) {
				TargetDataSource data = m.getAnnotation(TargetDataSource.class);
				DynamicDataSourceContextHolder.setDataSourceType(data.value());
				logger.info("DataSource：" + data.value());
			}else {
				//如果没有注解，则添加默认数据源
				//DynamicDataSourceContextHolder.setDataSourceType("default");
				//logger.info("DataSource：" + "default");
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}*/
	}

	@After("execution(* com.boy.springbootalldynamicdatasource.*.*(..))")
	public void restoreDataSource(JoinPoint point) {
		//DynamicDataSourceContextHolder.clearDataSourceType();
	}
}
