package com.guoqiang.commonlib.util;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


import com.google.common.collect.Lists;
/**
 * @Auther: zgq
 * @Date: 2018/6/23 15:15
 * @Description:
 */
public class ClassLoadUtil {
	
	public static <T> List<T> loadPlugs(String[] jarPaths,Class<T> interfaceClass){
		return loadPlugs(jarPaths, interfaceClass, false);
	}

	@SuppressWarnings({"unchecked", "resource" })
	/**
	 * 
	 * @param jarPaths
	 * @param interfaceClass
	 * @param autoLoad 是否动态加载，false：直接从类路径实例化
	 * @return
	 */
	public static <T> List<T> loadPlugs(String[] jarPaths,Class<T> interfaceClass,boolean autoLoad){
		List<T> tList = Lists.newArrayList();
		List<URL> urlList = Lists.newArrayList();
		List<File> flist = Lists.newArrayList();
		for (String fn : jarPaths) {
			File f = new File(fn);
			if(f.exists()){
				try {
					urlList.add(new URL("file:" + fn));
					flist.add(f);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}
		URLClassLoader loader = null;
		if (autoLoad){
			loader = new URLClassLoader(urlList.toArray(new URL[]{}),Thread.currentThread().getContextClassLoader());
		}
		for(File f:flist){
			JarFile jarFile = null;
			try {
				jarFile = new JarFile(f);
				Enumeration<JarEntry> es = jarFile.entries();
				while (es.hasMoreElements()) {
					JarEntry jarEntry = (JarEntry) es.nextElement();
					String name = jarEntry.getName();
					try {
						if(name != null && name.endsWith(".class")) {// 只解析了.class文件，没有解析里面的jar包
							String pkg = name.replace("/", ".").substring(0, name.length() - 6);
							Class<?> idClass;
							if (loader!=null&&autoLoad){
								idClass = loader.loadClass(pkg);
							}else{
								try{
									idClass = Class.forName(pkg);//从当前内存实例化
								}catch(ClassNotFoundException e){
									if(loader==null){
										loader = new URLClassLoader(urlList.toArray(new URL[]{}),Thread.currentThread().getContextClassLoader());
									}
									idClass = loader.loadClass(pkg);
									System.out.println("动态加载模块异常："+pkg);
								}
							}
							//抽象类和接口直接跳过
							if (idClass == null ||idClass.isInterface()||Modifier.isAbstract(idClass.getModifiers()) || !interfaceClass.isAssignableFrom(idClass)) {
								continue;
							}
							T obj = (T)idClass.newInstance();
							tList.add(obj);
						}
					} catch (java.lang.NoClassDefFoundError classNotFound) {
						//classNotFound.printStackTrace();
					} catch (Exception loadEx) {
						loadEx.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				try {
					jarFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return tList;
	}
}
