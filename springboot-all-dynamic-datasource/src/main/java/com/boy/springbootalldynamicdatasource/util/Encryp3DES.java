package com.boy.springbootalldynamicdatasource.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encryp3DES {
	private final static Logger logger = LoggerFactory.getLogger(Encryp3DES.class);
  private static final String Algorithm = "DESede"; // 定义 加密算法,可用
                            // DES,DESede(3DES) ,Blowfish
  private static final int keysize = 168;    // DES:56,DESede(3DES):112或168，Blowfish：32~448(必须8的倍数)
  
  // 加密方法
  // strKey为加密密钥
  // src为明文
  public static String encryptDes(String src) {
    try {
      // 生成密钥
      byte[] keyStr=getKeySpec();
      if(keyStr!=null){
        SecretKey deskey = new SecretKeySpec(keyStr, Algorithm);
        // 加密
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.ENCRYPT_MODE, deskey);
        return byte2hexString(c1.doFinal(src.getBytes()));
      }else
        return null;
    } catch (NoSuchAlgorithmException e1) {
      //e1.printStackTrace();
    	logger.error(e1.getMessage());
    } catch (javax.crypto.NoSuchPaddingException e2) {
    	logger.error(e2.getMessage());
    } catch (Exception e3) {
    	logger.error(e3.getMessage());
    }
    return null;
  }
  
  // 解密方法
  // strKey为解密密钥
  // src为密文
  public static String discryptDes(String src) {
    try {
      // 生成密钥
      byte[] keyStr=getKeySpec();
      if (keyStr!=null){
        SecretKey deskey = new SecretKeySpec(keyStr, Algorithm);
        // 解密
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.DECRYPT_MODE, deskey);
        return new String(c1.doFinal(hexStringToBytes(src)));
      }else
        return null;
    } catch (NoSuchAlgorithmException e1) {
    	logger.error("密文解密失败   "+e1.getMessage());
    } catch (javax.crypto.NoSuchPaddingException e2) {
    	logger.error("密文解密失败   "+e2.getMessage());
    } catch (Exception e3) {
    	logger.error("密文解密失败   "+e3.getMessage());
    }
    return null;
  }

  // 转换成十六进制字符串
  public static String byte2hexString(byte[] b) {
    String hs = "";
    String stmp = "";
    for (int n = 0; n < b.length; n++) {
      stmp = (Integer.toHexString(b[n] & 0XFF));
      if (stmp.length() == 1)
        hs = hs + "0" + stmp;
      else
        hs = hs + stmp;
      // if (n < b.length - 1)
      // hs = hs +":";
    }
    return hs.toUpperCase();
  }

  public static byte[] hexStringToBytes(String hexString) {
    if (hexString == null || hexString.equals("")) {
      return null;
    }
    hexString = hexString.toUpperCase();
    int length = hexString.length() / 2;
    char[] hexChars = hexString.toCharArray();
    byte[] d = new byte[length];
    for (int i = 0; i < length; i++) {
      int pos = i * 2;
      d[i] = (byte) ((charToByte(hexChars[pos]) << 4) | (charToByte(hexChars[pos + 1]) & 0xff));
    }
    return d;
  }

  private static byte charToByte(char c) {
    return (byte) "0123456789ABCDEF".indexOf(c);
  }
  //生成密钥文件
  public static void writeKeyFile(String strKey) throws IOException{
	  FileOutputStream  f=null;
	  ObjectOutputStream b=null;
	  
    try {
      //密钥生成
      KeyGenerator   kg   =   KeyGenerator.getInstance(Algorithm);
      SecureRandom   sr=new   SecureRandom(strKey.getBytes());//
      kg.init(keysize,sr);
      Key   key   =   kg.generateKey(); 
      URL url=Encryp3DES.class.getResource("/");
      f=new FileOutputStream(url.getPath()+"keyfile.dat");
      b=new  ObjectOutputStream(f);
      b.writeObject(key);
      b.flush();
    } catch (NoSuchAlgorithmException e) {
      logger.error(e.getMessage());
    }finally {    	
    	if(b!=null) {		
    		b.close();
    	}
    	if(f!=null) {
    		f.close();
    	}
    	
    	
    	
    }
  }
  
  
  
  public static byte[] getKeySpec() throws IOException{
    byte[] keyBytes=null;
    ObjectInputStream o=null;
    try {
      URL url=Encryp3DES.class.getResource("/");
      Resource r = new ClassPathResource("keyfile.dat");
      //LYB  WAS环境优化
//      if(r!=null){
        o = new ObjectInputStream(r.getInputStream());
        Key   key1   =  (Key) o.readObject(); 
        keyBytes = key1.getEncoded();
//      }
//      //读取密钥文件
//      File file=new File(url.getPath()+"keyfile.dat");
//      if (file.exists()){
//        FileInputStream f=new FileInputStream(url.getPath()+"keyfile.dat");
//        ObjectInputStream obj=new ObjectInputStream(f);
//        Key   key   =  (Key) obj.readObject(); 
//        keyBytes = key.getEncoded();
//      }
//      else
//        System.out.println("没找到密钥文件");
      //
      
    } catch (Exception e) {
      logger.error(e.getMessage());
    }finally {
    	if(o!=null) {
    		o.close();
    	}
    	
    }
    return keyBytes;
  }


  public static void main(String[] args) {
    Encryp3DES desc = new Encryp3DES();
    // 添加新安全算法,如果用JCE就要把它添加进去
//    // Security.addProvider(new com.sun.crypto.provider.SunJCE_b());
//    final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
//        0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
//        0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
//        (byte) 0xE2 }; 
    // 24字节的密钥
    String[] src = new String[]{"E94778EE14A5FE2076B09C2CC3A75A89"};
    for (int i = 0; i < src.length; i ++ ) {
      //System.out.println(desc.discryptDes(src[i]));
      System.out.println(desc.discryptDes(src[i]));
      System.out.println(desc.discryptDes(desc.encryptDes(src[i])));
      System.out.println(desc.encryptDes(src[i]));
    }
    
  
  }

}