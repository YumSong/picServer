package com.lames.picserver.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class FileUtil {
	
	private static final String REAL_TYPE_PATH = "/image/real-type.properties";
	private static final int HEADER_MIN_SIZE = 2;
	private static final int HEADER_MAX_SIZE = 10;
	private static Properties realType; 
	
	static {
		realType = loadReasource(REAL_TYPE_PATH);
	}

	public static String getFileType(byte[] buf) {
		String type = null;
		String header;
		for(int i=HEADER_MIN_SIZE; i<HEADER_MAX_SIZE && i<buf.length && type == null; ++i) {
			header = bytesToHexString(buf,0,i);
			type = realType.getProperty(header);
		}
		return type;
	}
	private static String bytesToHexString(byte[] src){
		return bytesToHexString(src,0,src.length);
	}
	
	private static String bytesToHexString(byte[] src,int start,int end) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (int i = start; i < end; i++) {
			hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			builder.append(hv);
		}
		return builder.toString();
	}
	
	public static void makeSureFileDir(String fileFullName) {
		fileFullName = fileFullName.replace("\\", "/");
		int index = fileFullName.lastIndexOf("/");
	
		if(index > 0) {
			makeSureDir(fileFullName.substring(0, index));
		}
	}
	
	public static void makeSureDir(String path) {
		File file = new File(path);
		if(!file.exists()) {
			file.mkdirs();
		}
	}
	
	public static boolean moveFile(String source, String dest) {
		File file = new File(source);
		if(file.exists()) {
			File destFile = new File(dest);
			if(destFile.exists()) {
				destFile.delete();
			}
			return file.renameTo(destFile);
		}
		return false;
	}
	
	public static Properties loadReasource(String path) {
		InputStream is = FileUtil.class.getResourceAsStream(path);
		Properties properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	

}
