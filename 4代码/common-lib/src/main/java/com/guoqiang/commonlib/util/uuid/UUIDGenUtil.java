package com.guoqiang.commonlib.util.uuid;

import java.util.UUID;

public class UUIDGenUtil {

	public static String randomUUID(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}
	
	public static void main(String[] args) {
		System.out.println(randomUUID());
	}
}
