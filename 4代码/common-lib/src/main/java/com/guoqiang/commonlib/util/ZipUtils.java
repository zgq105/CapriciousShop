package com.guoqiang.commonlib.util;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


/**
 * Description: 此类用于...
 * 
 * @author wunaigang(2005-6-21)
 * @version 1.0.0
 */
public class ZipUtils {
	
	public static final int FLUSH_BUFFER_SIZE = 10240; // buffer size=10K

	/**
	 * zip压缩功能测试. 将d:\\temp\\zipout目录下的所有文件连同子目录压缩到d:\\temp\\out.zip.
	 * 
	 * @param baseDir
	 *            所要压缩的目录名（包含绝对路径）
	 * @param objFileName
	 *            压缩后的文件名
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static void createZip(String baseDir, String objFileName) throws Exception {
		File folderObject = new File(baseDir);

		if (folderObject.exists()) {
			List fileList = getSubFiles(new File(baseDir));

			// 压缩文件名
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
					objFileName));

			ZipEntry ze = null;
			byte[] buf = new byte[1024];
			int readLen = 0;
			for (int i = 0; i < fileList.size(); i++) {
				File f = (File) fileList.get(i);
				//System.out.println("Adding: " + f.getPath() + f.getName());

				// 创建一个ZipEntry，并设置Name和其它的一些属性
				ze = new ZipEntry(getAbsFileName(baseDir, f));
				ze.setSize(f.length());
				ze.setTime(f.lastModified());

				// 将ZipEntry加到zos中，再写入实际的文件内容
				zos.putNextEntry(ze);
				InputStream is = new BufferedInputStream(new FileInputStream(f));
				while ((readLen = is.read(buf, 0, 1024)) != -1) {
					zos.write(buf, 0, readLen);
				}
				is.close();
				//System.out.println("done...");
			}
			zos.close();
		} else {
			throw new Exception("this folder isnot exist!");
		}
	}

	/**
	 * zip压缩功能测试. 将指定文件压缩后存到一压缩文件中
	 * 
	 * @param baseDir
	 *            所要压缩的文件名
	 * @param objFileName
	 *            压缩后的文件名
	 * @return 压缩后文件的大小
	 * @throws Exception
	 */
	public static long createFileToZip(String zipFilename, String sourceFileName)
			throws Exception {

		File sourceFile = new File(sourceFileName);

		byte[] buf = new byte[1024];

		// 压缩文件名
		File objFile = new File(zipFilename);

		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(objFile));

		ZipEntry ze = null;
		// 创建一个ZipEntry，并设置Name和其它的一些属性
		ze = new ZipEntry(sourceFile.getName());
		ze.setSize(sourceFile.length());
		ze.setTime(sourceFile.lastModified());

		// 将ZipEntry加到zos中，再写入实际的文件内容
		zos.putNextEntry(ze);

		InputStream is = new BufferedInputStream(
				new FileInputStream(sourceFile));

		int readLen = -1;
		while ((readLen = is.read(buf, 0, 1024)) != -1) {
			zos.write(buf, 0, readLen);
		}
		is.close();
		zos.close();

		return objFile.length();
	}

	/**
	 * zip压缩功能测试. 将指定文件压缩后存到一压缩文件中
	 * 
	 * @param baseDir
	 *            所要压缩的文件名
	 * @param objFileName
	 *            压缩后的文件名
	 * @return 压缩后文件的大小
	 * @throws Exception
	 */
	public static long createFileToZip(File sourceFile, File zipFile)
			throws IOException {

		byte[] buf = new byte[1024];

		// 压缩文件名
		File objFile = zipFile;

		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(objFile));

		ZipEntry ze = null;
		// 创建一个ZipEntry，并设置Name和其它的一些属性
		ze = new ZipEntry(sourceFile.getName());
		ze.setSize(sourceFile.length());
		ze.setTime(sourceFile.lastModified());

		// 将ZipEntry加到zos中，再写入实际的文件内容
		zos.putNextEntry(ze);

		InputStream is = new BufferedInputStream(
				new FileInputStream(sourceFile));

		int readLen = -1;
		while ((readLen = is.read(buf, 0, 1024)) != -1) {
			zos.write(buf, 0, readLen);
		}
		is.close();
		zos.close();

		return objFile.length();
	}

	/**
	 * 测试解压缩功能. 将d:\\download\\test.zip连同子目录解压到d:\\temp\\zipout目录下.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static void releaseZipToFile(String sourceZip, String outFileName)
			throws IOException {
		ZipFile zfile = new ZipFile(sourceZip);
		Enumeration zList = zfile.entries();
		ZipEntry ze = null;
		byte[] buf = new byte[1024];
		while (zList.hasMoreElements()) {
			// 从ZipFile中得到一个ZipEntry
			ze = (ZipEntry) zList.nextElement();
			if (ze.isDirectory()) {
				continue;
			}
			// 以ZipEntry为参数得到一个InputStream，并写到OutputStream中
			OutputStream os = new BufferedOutputStream(new FileOutputStream(
					getRealFileName(outFileName, ze.getName())));
			InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
			int readLen = 0;
			while ((readLen = is.read(buf, 0, 1024)) != -1) {
				os.write(buf, 0, readLen);
			}
			is.close();
			os.close();
			//System.out.println("Extracted: " + ze.getName());
		}
		zfile.close();

	}

	/**
	 * 取得指定目录下的所有文件列表，包括子目录.
	 * 
	 * @param baseDir
	 *            File 指定的目录
	 * @return 包含java.io.File的List
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getSubFiles(File baseDir) {
		List ret = new ArrayList();
		// File base=new File(baseDir);
		File[] tmp = baseDir.listFiles();
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i].isFile()) {
				ret.add(tmp[i]);
			}
			if (tmp[i].isDirectory()) {
				ret.addAll(getSubFiles(tmp[i]));
			}
		}
		return ret;
	}

	/**
	 * 给定根目录，返回一个相对路径所对应的实际文件名.
	 * 
	 * @param baseDir
	 *            指定根目录
	 * @param absFileName
	 *            相对路径名，来自于ZipEntry中的name
	 * @return java.io.File 实际的文件
	 */
	private static File getRealFileName(String baseDir, String absFileName) {
		String[] dirs = absFileName.split("/");
		// //System.out.println(dirs.length);
		File ret = new File(baseDir);
		// //System.out.println(ret);
		if (dirs.length > 1) {
			for (int i = 0; i < dirs.length - 1; i++) {
				ret = new File(ret, dirs[i]);
			}
		}
		if (!ret.exists()) {
			ret.mkdirs();
		}
		ret = new File(ret, dirs[dirs.length - 1]);
		return ret;
	}

	/**
	 * 给定根目录，返回另一个文件名的相对路径，用于zip文件中的路径.
	 * 
	 * @param baseDir
	 *            java.lang.String 根目录
	 * @param realFileName
	 *            java.io.File 实际的文件名
	 * @return 相对文件名
	 */
	private static String getAbsFileName(String baseDir, File realFileName) {
		File real = realFileName;
		File base = new File(baseDir);
		String ret = real.getName();
		while (true) {
			real = real.getParentFile();
			if (real == null)
				break;
			if (real.equals(base))
				break;
			else {
				ret = real.getName() + "/" + ret;
			}
		}
		//System.out.println("TTTTT" + ret);
		return ret;
	}

	public static void main(String args[]) {
		File inputFile = new File("E:\\unzip.zip");
		String outputPath = "E:";
		unzip(inputFile, outputPath);
	}
	
	
	/*
	 * 传进一个list集合的源文件,和一个新建好的解压文件
	 * */
	public static void zipFiles(List<File> srcfile, File zipfile) {    
        byte[] buf = new byte[2048];    
        try {    
            // 创建解压文件   
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));    
            // 压缩资源文件   
            for (int i = 0; i < srcfile.size(); i++) {    
                File file = srcfile.get(i);    
                FileInputStream in = new FileInputStream(file);    
                // 把文件添加到输出流   
                out.putNextEntry(new ZipEntry(file.getName()));    
                // 把文件写到压缩文件
                int len;    
                while ((len = in.read(buf)) > 0) {    
                    out.write(buf, 0, len);    
                }    
                out.closeEntry();    
                in.close();    
            }    
            out.close();   
        } catch (IOException e) {    
           e.printStackTrace();  
        }    
    }
	
	/**
	 * 解压缩，解决中文失败
	 * @param inputFile
	 * @param outputPath
	 * @return
	 */
	public static boolean unzip(File inputFile, String outputPath){
		FileOutputStream fileOut;
		File file;
		InputStream inputStream;
		org.apache.tools.zip.ZipFile zipFile = null;
		try {
			zipFile = new org.apache.tools.zip.ZipFile(inputFile,"GB18030");
			for (Enumeration<?> entries = zipFile.getEntries(); entries
					.hasMoreElements();) {
				org.apache.tools.zip.ZipEntry entry = (org.apache.tools.zip.ZipEntry) entries.nextElement();
				File outputPathDir = new File(outputPath);
				if (!outputPathDir.exists()) {
					outputPathDir.mkdirs();
				}
				file = new File(outputPath+File.separator + entry.getName());
				if (entry.isDirectory()) {
					file.mkdirs();
				} else {
					// 如果指定文件的目录不存在,则创建之.
					File parent = file.getParentFile();
					if (!parent.exists()) {
						parent.mkdirs();
					}
					inputStream = zipFile.getInputStream(entry);
					fileOut = new FileOutputStream(file);
					BufferedInputStream bis = new BufferedInputStream(
							inputStream, FLUSH_BUFFER_SIZE);
					int len = 0;
					byte[] buffer = new byte[FLUSH_BUFFER_SIZE];
					while ((len = bis.read(buffer, 0, FLUSH_BUFFER_SIZE)) > 0) {
						fileOut.write(buffer, 0, len);
					}
					fileOut.close();
					inputStream.close();
				}
			}
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		} finally{
			if(zipFile!=null){
				org.apache.tools.zip.ZipFile.closeQuietly(zipFile);
			}
		}
	}

}