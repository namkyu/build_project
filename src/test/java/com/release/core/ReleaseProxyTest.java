package com.release.core;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;


/**
 * @FileName : ReleaseProxyTest.java
 * @Project : build_project
 * @Date : 2013. 3. 18.
 * @작성자 : 이남규
 * @프로그램설명 :
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
