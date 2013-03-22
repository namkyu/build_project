package com.release.util;

import java.io.IOException;

import org.junit.Test;


/**
 * @FileName : ConfTest.java
 * @Project : build_project
 * @Date : 2013. 3. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class ConfTest {

	@Test
	public void confInitTest() throws IOException {
		Conf.init();

		System.out.println(Conf.getValue("ftp.remote.ip.list"));
		System.out.println(Conf.getIntValue("ftp.remote.port"));
		System.out.println(Conf.getValue("ftp.remote.user"));
		System.out.println(Conf.getValue("ftp.remote.password"));
		System.out.println(Conf.getValue("ftp.local.upload.directory"));
		System.out.println(Conf.getValue("ftp.remote.upload.directory"));
	}
}
