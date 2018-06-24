package com.guoqiang.commonlib.util;


import org.apache.shiro.util.ByteSource;
/**
 * @Auther: zgq
 * @Date: 2018/6/23 15:15
 * @Description:
 */
public class EDPasswordUtil {

	public static final String CREDENTIALS_SALT = "ch_EDP";
	
	/**
	 * 获得加密后的密码字符串
	 * 
	 * @param password
	 *            密码
	 * @return String
	 *            加密后的密码
	 */
	public static String getEDPHashedPasswordBase64(String password) {
		
		return new org.apache.shiro.crypto.hash.Sha256Hash(password,
				ByteSource.Util.bytes(CREDENTIALS_SALT)).toBase64();
	}
	
	public static boolean checkEDPPassword(String password,String source){
		String pwd = getEDPHashedPasswordBase64(password);
		//System.out.println("yw加密的:"+pwd+" 传入的密码:"+password+" 数据库存储的密码:"+source);
		return pwd.equals(source)||password.equals(source);
	}
	
	public static void main(String[] args) {
		String pwd = getEDPHashedPasswordBase64("123456");
		System.out.println(pwd);
	}
}
