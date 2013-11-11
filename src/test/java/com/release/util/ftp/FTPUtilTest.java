/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.util.ftp;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.SocketException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.oroinc.net.ftp.FTPClient;
import com.release.util.Conf;


/**
 * The Class FTPUtilTest.
 */
public class FTPUtilTest {

	private FTPClient client;

	@Before
	public void init() {
		client = new FTPClient();
	}

	@Test
	@Ignore
	public void ftpLogin() throws SocketException, IOException {
		Conf.init();
		client.connect("localhost", 21);
		boolean isFtpLogin = client.login("testuser", "1111");

		int reply = client.getReplyCode();
		assertThat(230, is(reply));
		assertThat(isFtpLogin, is(true));
	}
}
