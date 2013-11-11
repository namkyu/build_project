/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.junit.Test;


/**
 * The Class ConfTest.
 */
public class ConfTest {

	private final String FILE_NAME = "test.txt";

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

	@Test
	public void resourceTest() {
		URL url = getClass().getResource(FILE_NAME);
		String path = url.getPath();
		String fileName = path.substring(path.lastIndexOf("/") + 1);
		assertThat(fileName, is(FILE_NAME));
	}

	@Test
	public void resourceTest1() {
		URL url = getClass().getClassLoader().getResource("com/release/util/" + FILE_NAME);
		String path = url.getPath();
		String fileName = path.substring(path.lastIndexOf("/") + 1);
		assertThat(fileName, is(FILE_NAME));
	}

	@Test
	public void resourceTest2() throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream("com/release/util/" + FILE_NAME);
		byte[] bts = new byte[4];
		is.read(bts);
		assertThat("test", is(new String(bts)));
	}

	@Test(expected = NullPointerException.class)
	public void resourceTest3() {
		URL url = getClass().getClassLoader().getResource("/com/release/util/" + FILE_NAME);
		url.getPath();
	}
}
