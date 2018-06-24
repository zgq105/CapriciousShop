package com.guoqiang.commonlib.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * zgq
 * Gson工具类
 */
public class GsonUtil {
	
	private static Gson gson;


	/**
	 * 初始化gson设置
	 * @return
	 */
	public static Gson build() {
		if(gson==null){
			gson = new GsonBuilder()
			.serializeNulls()// 允许序列化null成员
			.setDateFormat("yyyy-MM-dd HH:mm:ss")//设置日期格式
			.create();
		}
		return gson;
	}
}
