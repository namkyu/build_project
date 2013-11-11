/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.core;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;


/**
 * The Class ReleaseProxyTest.
 */
public class ReleaseProxyTest {

	@Test
	public void 로더테스트() throws ClassNotFoundException, IOException {
		URL url = getClass().getResource("/com/release/handler");
		System.out.println(url.getFile());

		URL url1 = getClass().getClassLoader().getResource("com/release/handler");
		System.out.println(url1.getFile());
	}
}
