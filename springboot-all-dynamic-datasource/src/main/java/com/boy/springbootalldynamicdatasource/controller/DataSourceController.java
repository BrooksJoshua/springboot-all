package com.boy.springbootalldynamicdatasource.controller;

import com.boy.springbootalldynamicdatasource.bean.DBType;
import com.boy.springbootalldynamicdatasource.bean.DataSource;
import com.boy.springbootalldynamicdatasource.bean.RoleUser;
import com.boy.springbootalldynamicdatasource.bean.UserInfo;
import com.boy.springbootalldynamicdatasource.datasource.DynamicDataSourceContextHolder;
import com.boy.springbootalldynamicdatasource.service.DataSourceService;
import com.boy.springbootalldynamicdatasource.util.DBHelper;
import com.boy.springbootalldynamicdatasource.util.Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-01 16:27
 */

@Controller
@RequestMapping(value = "dataSource")
public class DataSourceController extends BasicController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private DataSourceService dataSourceService;


    private static String  DATASOURCE="DATASOURCE";

    @ApiOperation(value = "数据源列表", notes = "返回json")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody
    Object list(String dsrcCode, String dsrcName,
                String dbType, Integer pageNum, Integer rowNum, HttpServletRequest request) {
        String ex="";
        try {
            DynamicDataSourceContextHolder.setDataSourceType("default");
            UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userinfo");
            DataSource dataSource = new DataSource();
            dataSource.setDsrcCode(dsrcCode);
            dataSource.setDsrcName(dsrcName);
            dataSource.setDbType(DBType.getByCode(dbType));

            RoleUser roleUser=new RoleUser();
            roleUser.setUserId(userInfo.getId());
            roleUser.setRoleId("1");
            //List<RoleUser> roleUsers=roleUserService.findList(roleUser);
//            if(Utils.isEmpityCollection(roleUsers)){
//                dataSource.setUserId(userInfo.getId());
//            }
            PageHelper.startPage(pageNum!=null?pageNum:1, rowNum!=null?rowNum:25);
            List<DataSource> list = dataSourceService.findList(dataSource);
            PageInfo<DataSource> page = new PageInfo<DataSource>(list);
            return page ;//this.outBound(Base64Util.jdkBase64Encoder(this.getDataInfo(new User(),page.getList(),page.getTotal())));
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("------------->/dataSource/list 接口异常" + e.getMessage());
            ex=e.getMessage();
            return this.errorHandler("数据源列表加载失败");
        }finally {
            //logService.saveLog(request,ex,null,DATASOURCE,"列表查看");
        }
    }


    @ApiOperation(value = "数据源列表", notes = "返回json")
    @RequestMapping(value = "/allList", method = RequestMethod.POST)
    public @ResponseBody Object allList(String dsrcCode, String dsrcName,
                                        String dbType,HttpServletRequest request) {
        String ex="";
        try {
            DynamicDataSourceContextHolder.setDataSourceType("defaut");
//			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userinfo");
            DataSource dataSource = new DataSource();
            dataSource.setDsrcCode(dsrcCode);
            dataSource.setDsrcName(dsrcName);
//			RoleUser roleUser=new RoleUser();
//			roleUser.setUserId(userInfo.getId());
//			roleUser.setRoleId("1");
//			List<RoleUser> roleUsers=roleUserService.findList(roleUser);
//			if(Utils.isEmpityCollection(roleUsers)){
//				dataSource.setUserId(userInfo.getId());
//			}
            dataSource.setDbTypeStr(dbType);
            List<DataSource> list = dataSourceService.findList(dataSource);
            return this.outBound(list);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("------------->/dataSource/allList 接口异常" + e.getMessage());
            ex=e.getMessage();
            return this.errorHandler("数据源列表加载失败");
        }
    }



    @ApiOperation(value = "获取数据源类型信息", notes = "返回json")
    @RequestMapping(value = "/getDBType", method = RequestMethod.GET)
    public @ResponseBody Object getDBType() {
        try {
            return this.outBound(DBType.values());
        } catch (Exception e) {
            logger.error("------------->/dataSource/getDBType 接口异常" + e.getMessage());
            return this.errorHandler(e);
        }
    }

    @ApiOperation(value = "校验系统编码是否存在", notes = "返回json")
    @RequestMapping(value = "/validateDsrcCode", method = RequestMethod.GET)
    public @ResponseBody Object validateDsrcCode(String dsrcCode) {
        try {
            // 动态切换数据源
            DynamicDataSourceContextHolder.setDataSourceType("defaut");
            DataSource dataSource = new DataSource();
            dataSource.setDsrcCode(dsrcCode);
            List<DataSource> list = dataSourceService.findListByDsrcCode(dsrcCode);
            if (list != null && list.size() > 0) {
                return this.outBound(true);
            }

            return this.outBound(false);
        } catch (Exception e) {
            logger.error("------------->/dataSource/validateDsrcCode 接口异常" + e.getMessage());
            return this.errorHandler(e);
        }
    }

    @ApiOperation(value = "保存数据源信息", notes = "返回json")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public @ResponseBody Object insert(@RequestBody DataSource dataSource, HttpServletRequest request) {
        String ex="";
        try {

            DataSource source = new DataSource();
            source.setDsrcCode(dataSource.getDsrcCode());
            List<DataSource> list = dataSourceService.findList(source);
            if (list != null && list.size() > 0) {
                return this.errorHandler("系统Code重复");
            }
            if(!Utils.isEmptyStr(dataSource.getIp()))
                dataSource.setIp(dataSource.getIp().trim());
            if(!Utils.isEmptyStr(dataSource.getPort()))
                dataSource.setPort(dataSource.getPort().trim());
            if(!Utils.isEmptyStr(dataSource.getPassword()))
                dataSource.setPassword(dataSource.getPassword().trim());

            // 动态切换数据源
            DynamicDataSourceContextHolder.setDataSourceType("defaut");
            dataSource.setBeanName(RandomStringUtils.randomAlphabetic(8));
            //注释了 dataSource.setPassword(En3DES加密);
            //设置URL
            dataSource.setUrl(DBHelper.getJDBCUrl(dataSource.getDbType(), dataSource.getIp(), dataSource.getPort(), dataSource.getServiceName()));
            dataSourceService.save(dataSource);
            dataSourceService.addDataSource(dataSource);
            return this.outBound("保存成功");
        } catch (Exception e) {
            logger.error("------------->/dataSource/validateDsrcCode 接口异常" + e.getMessage());
            ex=e.getMessage();
            return this.errorHandler("保存失败");
        }finally {
            //logService.saveLog(request,ex, JSONObject.toJSONString(dataSource),"DATASOURCE","新增数据源");
        }
    }

    @ApiOperation(value = "修改数据源信息", notes = "返回json")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody Object update(@RequestBody DataSource dataSource,HttpServletRequest request) {
        String ex="";
        try {

            DataSource source = new DataSource();
            source.setDsrcCode(dataSource.getDsrcCode());
            List<DataSource> list = dataSourceService.findList(source);
            for(DataSource d : list) {
                if(!d.getId().equals(dataSource.getId()) && d.getDsrcCode().equals(dataSource.getDsrcCode())) {
                    return this.errorHandler("系统Code重复");
                }
            }

            if(!Utils.isEmptyStr(dataSource.getIp()))
                dataSource.setIp(dataSource.getIp().trim());
            if(!Utils.isEmptyStr(dataSource.getPort()))
                dataSource.setPort(dataSource.getPort().trim());
            if(!Utils.isEmptyStr(dataSource.getPassword()))
                dataSource.setPassword(dataSource.getPassword().trim());

            // 动态切换数据源
            DynamicDataSourceContextHolder.setDataSourceType("defaut");
            if (dataSource.getPassword() != null && !dataSource.getPassword().equals("")) {
                //注释了 dataSource.setPassword(En3DES加密);
            }

            //设置URL
            dataSource.setUrl(DBHelper.getJDBCUrl(dataSource.getDbType(), dataSource.getIp(), dataSource.getPort(), dataSource.getServiceName()));
            dataSourceService.update(dataSource);

            dataSourceService.removeDataSource(dataSource.getId(), dataSource.getBeanName());
            dataSourceService.addDataSource(dataSource);

            return this.outBound("修改成功");
        } catch (Exception e) {
            logger.error("------------->/dataSource/update 接口异常" + e.getMessage());
            ex=e.getMessage();
            return this.errorHandler("修改失败");
        }finally {
            //logService.saveLog(request,ex,JSONObject.toJSONString(dataSource),DATASOURCE,"修改数据源");
        }
    }

    @ApiOperation(value = "获取数据源信息", notes = "返回json")
    @RequestMapping(value = "/getDataSource", method = RequestMethod.POST)
    public @ResponseBody Object getTable(String id,HttpServletRequest request) {
        String ex="";
        try {
            // 动态切换数据源
            DynamicDataSourceContextHolder.setDataSourceType("defaut");
            DataSource dataSource = dataSourceService.get(id);
            //解密
            return dataSource; //this.outBound(Base64Util.jdkBase64Encoder(JSONObject.toJSONString(dataSource)));
        } catch (Exception e) {
            logger.error("------------->/dataSource/getDataSource 接口异常" + e.getMessage());
            ex=e.getMessage();
            return this.errorHandler("数据源信息加载失败");
        }finally {
            //logService.saveLog(request,ex,null,DATASOURCE,"数据源详情");
        }
    }

    @ApiOperation(value = "删除数据源信息", notes = "返回json")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody Object delete(String id,HttpServletRequest request) {
        String ex="";
        try {
            // 动态切换数据源
            DynamicDataSourceContextHolder.setDataSourceType("defaut");
            DataSource dataSource = dataSourceService.get(id);
            dataSourceService.delete(dataSource);
            dataSourceService.removeDataSource(dataSource.getId(),dataSource.getBeanName());
            return this.outBound("删除成功");
        } catch (Exception e) {
            logger.error("------------->/dataSource/delete 接口异常" + e.getMessage());
            ex=e.getMessage();
            return this.errorHandler("保存失败");
        }finally {
            //logService.saveLog(request,ex,null,DATASOURCE,"删除数据源");
        }
    }

    @RequestMapping(value = "/checkConnection", method = RequestMethod.POST)
    @ApiOperation(value = "检测数据库连接", notes = "返回json")
    public @ResponseBody Object checkConnection(@RequestBody DataSource dataSource) {
        try {

            if(!Utils.isEmptyStr(dataSource.getIp()))
                dataSource.setIp(dataSource.getIp().trim());
            if(!Utils.isEmptyStr(dataSource.getPort()))
                dataSource.setPort(dataSource.getPort().trim());
            if(!Utils.isEmptyStr(dataSource.getPassword()))
                dataSource.setPassword(dataSource.getPassword().trim());
            //设置URL
            dataSource.setUrl(DBHelper.getJDBCUrl(dataSource.getDbType(), dataSource.getIp(), dataSource.getPort(), dataSource.getServiceName()));
            boolean b = DBHelper.checkConnectionDB(dataSource.getDbType().getDriverClass(), dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
            if(b) {
                return this.outBound("连接成功");
            }else {
                throw new Exception("连接失败");
            }

        } catch (Exception e) {
            logger.error("------------->/dataSource/checkConnection 接口异常"+e.getMessage());
            return this.errorHandler(e);
        }
    }



}
