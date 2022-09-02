package com.boy.springbootalldynamicdatasource.util;


import org.apache.commons.lang3.StringUtils;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 对象判断工具类
 */
public class Utils {
	private static Logger logger = Logger.getLogger(Utils.class);
    /**
     * 判断一个字符串是否为空
     *
     * @param str
     *
     * @return
     */
    public static boolean isEmptyStr(String str){
        if(str==null){
            return true;
        }
        str=str.trim();
        return str.isEmpty();
    }
    
    
    /**
     * 判断一个集合是否为空
     *
     * @param c
     *
     * @return
     */
    public static boolean isEmptyCollection(Collection c){
        if(c==null || c.isEmpty()){
            return true;
        }else {
        	return false;
        }
    }

    /**
     * 对集合去重 （无顺序）
     *
     * @param arlList 带去重的集合
     *
     * @return 返回去重后的集合
     */
    public static List removeDuplicate(List arlList){
        if(Utils.isEmpityCollection(arlList)){
            return new ArrayList();
        }
        HashSet h=new HashSet(arlList);
        arlList.clear();
        arlList.addAll(h);
        return arlList;
    }
    /**
     * 判断对象是否为空
     *
     * @param obj
     *
     * @return
     */
    public static boolean isNotNull(Object obj){
        if(obj!=null){
            return true;
        }
        return false;
    }
    /**
     * 判断集合是否为空
     *
     * @param c 待判断的集合
     *
     * @return集合为空,返回true ,反之 false
     */
    public static boolean isEmpityCollection(Collection c){
        if(c==null||c.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 判断Map是否为空
     *
     * @param map 待判断的集合
     *
     * @return集合为空,返回true ,反之 false
     */
    public static boolean isEmpityMap(Map map){
        if(map==null){
            return true;
        }else{
            if(map.isEmpty()){
                return true;
            }else{
                return false;
            }
        }
    }

    public static boolean isEmptyStr(Object s){
        return (s==null)||(s.toString().trim().length()==0);
    }
    /**
     * 判断传入参数是否为空,空字符串""或"null"或"<null> 为了兼容ios的空获取到<null>字符串
     *
     * @param s 待判断参数
     *
     * @return true 空 <br>
     * false 非空
     */
    public static boolean isEmptyString(Object s){
        return (s==null)||(s.toString().trim().length()==0)||s.toString().trim().equalsIgnoreCase("null")||s.toString().trim().equalsIgnoreCase("<null>");
    }
    /**
     * 对传入参数进行判断是否为空,为空则返回"",反之返回传入参数
     *
     * @param v 传入参数
     *
     * @return 处理后的参数
     */
    public static String filterNullValue(String v){
        return isEmptyString(v)?"":v;
    }



    /**
     * 将base64编码的字符串转换成图片保存到相应的路径
     *
     * @param imageInf 图片 字符串
     * @param path     保存的路径
     *
     * @return 是否保存成功状态
     *
     * @throws IOException IO读写错误
     */
    public static boolean saveFileToDisk(String imageInf,String path) throws IOException{//对字节数组字符串进行Base64解码并生成图片
    	OutputStream out=null;
    	try{
            if(imageInf==null){
                //图像数据为空
                return false;
            }
            //Base64解码
            byte[] b= Base64.decodeBase64(imageInf);
            for(int i=0;i<b.length;++i){
                if(b[i]<0){//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            out=new FileOutputStream(path);
            out.write(b);
            out.flush();
           
            return true;
        }catch(IOException e){
            logger.error(e.getMessage());
            return false;
        }finally {
        	if(out!=null) {
        		 out.close();
        	}
        
        }
    }

    /**
     * 从servletRequest inputStream 获取原始字符串
     * @param request
     * @return
     */
    public static String getStrFromReqInputStrem(ServletRequest request){
        try {
            if(request == null) return "";
            InputStream in = request.getInputStream();
            int len = request.getContentLength();
            if(len <= 0) return "";
            byte[] buffer = new byte[len];
            while(in.read(buffer)>0) {
            	String s = new String(buffer,"UTF-8");
            	 return s;
            }
            return "";     
        }catch (Exception e){
            logger.error(e.getMessage());
            return "";
        }

    }
    /**
     * 验证手机号码、邮箱，邮编的合法性
     *
     * @param objValue 手机号码、邮箱，邮编
     *
     * @return 返回手机号码、邮箱，邮编的合法性 true 合法， false 不合法
     */
    public static Boolean CheckParamValidity(String objValue,String tip){
        if(isEmptyString(objValue)){
            return false;
        }
        String regExp = "^((13[0-9])|(15[^4,\\D])|(17[^4,\\D])|(18[0,5-9]))\\d{8}$";//默认手机
        if("1".equals(tip)){//邮箱
            regExp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        }else if("2".equals(tip)){//邮编
            regExp = "[1-9]\\d{5}(?!\\d)";
        }
        Pattern p=Pattern.compile(regExp);
        Matcher m=p.matcher(filterNullValue(objValue));
        return m.matches();
    }

    /**
     * 获取远程IP地址
    * @Title: getRemoteIp 
    * @author Jason
    * @param @return     
    * @return String
    * @throws
     */
    public static String getRemoteIp(){
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	if (request.getHeader("x-forwarded-for") == null) { 
            return request.getRemoteAddr(); 
        } 
        return request.getHeader("x-forwarded-for"); 
    }



    /**
     * 获取map中第一个数据值
     * @param <K> Key的类型
     * @param <V> Value的类型
     * @param map 数据源
     * @return 返回的值
     */
    public static <K, V> V getFirstOrNull(Map<K, V> map) {
        V obj = null;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            obj = entry.getValue();
            if (obj != null) {
                break;
            }
        }
        return obj;
    }

    /**
     * 获取map中的所有值，并去重
     * @param map
     * @return
     */
    public static List getDuplicateList(Map<String,String> map) {
        Object obj = null;
        List list = new ArrayList();
        for (Map.Entry<String,String> entry : map.entrySet()) {
            obj = entry.getValue();
            if (obj != null) {
                list.add(obj);
            }
        }
        return removeDuplicate(list);
    }
    
    
    /**
     * 正则表达式匹配两个指定字符串中间的内容
     * @param soap
     * @return
     */
    public static List<String> getSubUtil(String soap,String rgex){
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        return list;
    }
    
    /**
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     * @param soap
     * @param rgex
     * @return
     */
    public static String getSubUtilSimple(String soap,String rgex){
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        String s="";
        while(m.find()){
            s= m.group(1);
        }
        return s;
    }


    /**
     *
     * @param param 要处理的字符串
     * @param length 显示*号的位数
     * @return
     */
    public static String hidePart(String param, int length) {
        return StringUtils.leftPad(StringUtils.right(param, length), StringUtils.length(param), "*");
    }

    public static String getRandomCode(int number) throws Exception{
        String codeNum = "";
        int[] code = new int[3];
        SecureRandom secureRandom=new SecureRandom();
        for (int i = 0; i < number; i++) {
            int num = secureRandom.nextInt(10) + 48;
            int uppercase = secureRandom.nextInt(26) + 65;
            int lowercase = secureRandom.nextInt(26) + 97;
            code[0] = num;
            code[1] = uppercase;
            code[2] = lowercase;
            codeNum += (char) code[secureRandom.nextInt(3)];
        }
        return codeNum;
    }

    public static String getRandomNumCode(int number) throws Exception{
        String codeNum = "";
        int [] numbers = {0,1,2,3,4,5,6,7,8,9};
       // Random random = new Random();
        SecureRandom secureRandom=new SecureRandom();
        for (int i = 0; i < number; i++) {
            int next = secureRandom.nextInt(10000);//目的是产生足够随机的数，避免产生的数字重复率高的问题
            codeNum+=numbers[next%10];
        }
        //System.out.println(codeNum);
        return codeNum;
    }
}
