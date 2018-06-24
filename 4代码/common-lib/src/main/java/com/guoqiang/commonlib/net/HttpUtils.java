package com.guoqiang.commonlib.net;


import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.guoqiang.commonlib.util.json.GsonUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;


/**
 * 网络请求器
 * @author zgq
 *
 */
public class HttpUtils {

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String httpGet(String url) throws Exception {
		String response = null;
		// 请求服务
		HttpClient http = new HttpClient();
		HttpMethod method = new GetMethod(url.toString());
		int state = http.executeMethod(method);
		if (state == HttpStatus.SC_OK) {
			response = method.getResponseBodyAsString();
		}
		return response;
	}
	
	/**
	 * post请求
	 * @param url
	 * @param map 需要post提交的数据
	 * @return
	 * @throws Exception
	 */
	public static String httpPost(String url,Map<String, String> map) throws Exception{
		String response = null;
		// 请求服务
		HttpClient http = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		NameValuePair []nameValuePairs=new NameValuePair[map.size()];
		Iterator<Entry<String, String>> iterator=map.entrySet().iterator();
		for (int i = 0; iterator.hasNext(); i++) {
			Entry<String, String> entry=iterator.next();
			String key=entry.getKey();
			String value=entry.getValue();
			NameValuePair nameValuePair=new NameValuePair(key, value);
			nameValuePairs[i]=nameValuePair;
		}
		postMethod.setRequestBody(nameValuePairs);
		int state = http.executeMethod(postMethod);
		if (state == HttpStatus.SC_OK) {
			response = postMethod.getResponseBodyAsString();
		}
		return response;
	}
	
	/**
	 * post请求
	 * @param url
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static String httpPost(String url,InputStream inputStream) throws Exception{
		String response = null;
		// 请求服务
		HttpClient http = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestBody(inputStream);
		int state = http.executeMethod(postMethod);
		if (state == HttpStatus.SC_OK) {
			response = postMethod.getResponseBodyAsString();
		}
		return response;
	}
	
	/**
	 * 获取http请求
	 */
	public static <T> T httpGetEntity(String url, Class<T> cls) {
		try {
			String json = httpGet(url);
			return GsonUtil.build().fromJson(json, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
