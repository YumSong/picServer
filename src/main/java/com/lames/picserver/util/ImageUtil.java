package com.lames.picserver.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

public class ImageUtil {
	
	private static final Set<String> IMAGE_TYPE = new HashSet<String>();
	
	static {
		IMAGE_TYPE.add("jpg");
		IMAGE_TYPE.add("png");
		IMAGE_TYPE.add("gif");
	}

	public static String saveImage(InputStream is,String destPath) throws IOException, NoSuchAlgorithmException {
		String url = null;
		byte[] headerBuf = new byte[4];
		is.read(headerBuf);
		String type = FileUtil.getFileType(headerBuf);
		System.out.println(type);
		if(isImage(type)) {
			String tempPath = destPath + "/temp/";
			String tempFileFullName = tempPath + System.currentTimeMillis() + "";
			FileUtil.makeSureDir(tempPath);
			
			OutputStream tempOs = new FileOutputStream(tempFileFullName);
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			tempOs.write(headerBuf);
			md.update(headerBuf);
			
			byte[] buf = new byte[8196];
			int len = -1;
			
			while((len = is.read(buf)) != -1) {
				md.update(buf, 0, len);
				tempOs.write(buf, 0, len);
			}
			String md5Code = new BigInteger(1,md.digest()).toString(16);
			String destFileFullName = getMD5FullName(md5Code) + "." + type;
			FileUtil.makeSureFileDir(destPath + destFileFullName);
			tempOs.close();
			if(FileUtil.moveFile(tempFileFullName, destPath + destFileFullName)) {
				url = destFileFullName;
			}
		}
	
		return url;
	}
	
	public static String getMD5FullName(String md5Code) {
		StringBuilder sb = new StringBuilder(md5Code);
		for(int i = 0; i<md5Code.length(); i += 8) {
			sb.insert(i, '/');
		}
		return sb.toString();
	}
	
	public static boolean isImage(String type) {
		return IMAGE_TYPE.contains(type);
	}
}
