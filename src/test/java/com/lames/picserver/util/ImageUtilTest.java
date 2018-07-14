package com.lames.picserver.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import junit.framework.TestCase;

public class ImageUtilTest extends TestCase {

	@Test
	public void testSaveImage() throws IOException, NoSuchAlgorithmException {
		String path = "D:\\My Pictures\\R.M.S.jpg";
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
		ImageUtil.saveImage(bis,"D:/image/real");
	}
}
